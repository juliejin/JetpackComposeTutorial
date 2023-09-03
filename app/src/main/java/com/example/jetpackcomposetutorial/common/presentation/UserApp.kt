package com.example.jetpackcomposetutorial.common.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposetutorial.common.data.repository.UserRepository
import com.example.jetpackcomposetutorial.features.userDetail.presentation.view.UserDetailScreen
import com.example.jetpackcomposetutorial.features.userList.presentation.view.UserListScreen

@Composable
fun UserApp() {
    val navControler = rememberNavController()
    UserNavHost(navControler)
}

@Composable
fun UserNavHost(navController: NavHostController) {
    val userViewModel = UserViewModel(UserRepository(LocalContext.current))
    NavHost(navController = navController, startDestination = "userList") {

        composable("userList") {
            UserListScreen(onUserClick = {
                navController.navigate("userDetail/${it.id}")
            }, viewModel = userViewModel)
        }

        composable("userDetail/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            UserDetailScreen(
                userViewModel,
                userId ?: "",
                toDetails = {
                    navController.navigate("userDetail/$it")
                }
            )
        }
    }
}