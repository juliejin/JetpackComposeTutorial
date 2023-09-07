package com.example.jetpackcomposetutorial.features.userDetail.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackcomposetutorial.MainActivity
import com.example.jetpackcomposetutorial.common.presentation.UserViewModel

@Composable
fun UserDetailScreen(
    userViewModel: UserViewModel = hiltViewModel(LocalContext.current as MainActivity),
    userId: String,
    toDetails: (userId: String) -> Unit
) {
/**
 * userViewModel.userLiveData.asStateFlow() will cause error
 * "StateFlow.value should not be called within composition"
 * Because in composition you should be listening to events, not just reading the current one.
 * with StateFlow.value you just read the current value
 * If you read only the current one then your view won't get recomposed after the value is changed
 *
 * Under the hood, the implementation of collectAsStateWithLifecycle uses the repeatOnLifecycle
 *
 * collectAsState will have app still collects from our Flow while it's in background.
 * */
    val userDataState = userViewModel.userLiveData.collectAsStateWithLifecycle()
    userViewModel.getUserDataById(userId)
    Card(
        modifier = Modifier.fillMaxSize()
    ) {
        Column (modifier = Modifier.fillMaxSize()) {
            Text(
                text = "${userDataState.value?.first_name} ${userDataState.value?.last_name}",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = "${userDataState.value?.email}",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = "Friends",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.displaySmall
            )
            userDataState.value?.friends_user_ids?.forEach { friendId ->
                userViewModel.getUserDetailsById(friendId.toString())?.let { userData ->
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                toDetails(userData.id.toString())
                            }
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = userData.first_name,
                                modifier = Modifier.padding(10.dp),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}