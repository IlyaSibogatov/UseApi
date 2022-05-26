package com.example.useapi.model

class ReqresUserListApiResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_page: Int,
    val data: List<ReqresUser>
)