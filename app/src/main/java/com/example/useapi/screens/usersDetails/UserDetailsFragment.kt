package com.example.useapi.screens.usersDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.useapi.R
import com.example.useapi.ResultState
import com.example.useapi.databinding.UserDetailsFragmentBinding

class UserDetailsFragment : Fragment(R.layout.user_details_fragment) {

    private lateinit var viewModel: UserDetailsViewModel
    private lateinit var controller: NavController

    private var _binding: UserDetailsFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller = Navigation.findNavController(view)
        _binding = UserDetailsFragmentBinding.bind(view)
        val id = arguments?.getLong(ARGUMENT_ID) ?: INVALID_ID

        initViewModel(id)

        binding.apply {
            bckBtn.setOnClickListener {
                controller.popBackStack()
            }
        }
        viewModel.user.observe(viewLifecycleOwner) {
            when (it) {
                is ResultState.Error -> binding.txtDetailsTitle.text = "ERROR"
                is ResultState.Loading -> binding.txtDetailsTitle.text = "Loading"
                is ResultState.Success -> {
                    binding.tvFirstName.text = it.data.first_name;
                    binding.tvLastName.text = it.data.last_name;
                    binding.tvAddress.text = it.data.email
                    binding.txtDetailsTitle.text = "Details about user"
                }
            }
        }
    }

    private fun initViewModel(id: Long) {
        val factory = UserDetailsViewModelFactory(id)
        viewModel = ViewModelProvider(this, factory)[UserDetailsViewModel::class.java]
    }

    private fun hideUserDetailsViews() {

    }

    companion object {
        private const val ARGUMENT_ID = "ARGUMENT_ID"
        private const val INVALID_ID = -1L
    }
}