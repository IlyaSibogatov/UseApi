package com.example.useapi.repository

enum class UserSortOrder {
    NONE,
    FIRST_NAME_ASC,
    FIRST_NAME_DESC
}
data class UserQuery(
    val sortOrder: UserSortOrder = UserSortOrder.NONE,
    val searchQuery: String? = null
) {
    fun hasSearchQuery() =! searchQuery.isNullOrBlank()
}
