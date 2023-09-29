package com.example.jetpackcomposetutorial.common.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.presentation.features.userDetail.UserDetailScreen
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.presentation.features.userList.UserListScreen

@Composable
fun UserApp() {
    val navControler = rememberNavController()
    UserNavHost(navControler)
}

@Composable
fun UserNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "userList") {

        composable("userList") {
            UserListScreen(
                onUserClick = {
                navController.navigate("userDetail/${it.id}")
            },
                paging = false)
        }

        composable("userDetail/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            UserDetailScreen(

                userId = userId ?: "",
                toDetails = {
                    navController.navigate("userDetail/$it")
                }
            )
        }
    }
}