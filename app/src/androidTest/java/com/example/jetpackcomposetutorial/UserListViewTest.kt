package com.example.jetpackcomposetutorial

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.jetpackcomposetutorial.common.data.repository.UserRepository
import com.example.jetpackcomposetutorial.common.presentation.UserViewModel
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.presentation.features.userList.UserListScreen
import org.junit.Rule
import org.junit.Test

class UserListViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testUserList() {
        composeTestRule.setContent {
            val userViewModel = UserViewModel(UserRepository(LocalContext.current))
            UserListScreen(onUserClick = {}, viewModel = userViewModel)
        }
        composeTestRule.onNodeWithText("Alice Smith").assertIsDisplayed()
    }
}