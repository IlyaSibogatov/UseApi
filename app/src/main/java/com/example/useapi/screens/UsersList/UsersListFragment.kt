package com.example.useapi.screens.UsersList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.useapi.R
import com.example.useapi.ResultState
import com.example.useapi.databinding.UserListFragmentBinding
import com.example.useapi.model.ReqresUser
import kotlinx.android.synthetic.main.user_list_fragment.*


class UsersListFragment: Fragment(R.layout.user_list_fragment) {

    private lateinit var rvAdapter: UsersListRecyclerAdapter
    private lateinit var viewModel: UsersListViewModel
    private lateinit var controller: NavController

    private var _binding: UserListFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvAdapter = UsersListRecyclerAdapter(
            layoutInflater,
            object :UsersListRecyclerAdapter.UserItemClickListener{
                override fun onUserClicked(user: ReqresUser) {
                    val bundle = Bundle()
                    bundle.putLong(ARGUMENT_ID,user.id)
                    controller.navigate(
                        R.id.action_usersListFragment_to_userDetailsFragment, bundle)
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller = Navigation.findNavController(view)
        _binding = UserListFragmentBinding.bind(view)

        initViewModel()

        binding.apply {
            rv_users_list.adapter = rvAdapter
        }
        viewModel.users.observe(viewLifecycleOwner){
            when(it){
                is ResultState.Error -> binding.listTitle.text = "ERROR"
                is ResultState.Loading -> binding.listTitle.text = "Loading"
                is ResultState.Success -> rvAdapter.setList(it.data).also { binding.listTitle.text = "Users list" }
            }
        }
    }

    private fun initViewModel(){
        val factory = UsersListViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[UsersListViewModel::class.java]
    }

    companion object {
        private const val ARGUMENT_ID = "ARGUMENT_ID"
    }
}
