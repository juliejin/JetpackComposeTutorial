package com.example.jetpackcomposetutorial.common.data.model

data class UserData(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val friends_user_ids: List<Int>,
    val hobbies: List<String>,
    val icon_url: String
)