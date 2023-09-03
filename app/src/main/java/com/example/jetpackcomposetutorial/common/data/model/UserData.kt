package com.example.jetpackcomposetutorial.common.data.model

data class UserData(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val age: Int,
    val last_login_date: String,
    val friends_user_ids: List<Int>,
    val favorite_animal: String,
    val least_favorite_smell: String,
    val hobbies: List<String>,
    val birthplace: String,
    val favorite_color: String,
    val unread_messages: Int,
    val has_pets: Boolean,
    val shoe_size: Double,
    val coffee_preference: String,
    val dream_destination: String,
    val music_genre: String,
    val is_vegan: Boolean,
    val fitness_level: String,
    val lucky_number: Int
)