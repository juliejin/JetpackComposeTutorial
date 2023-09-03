package com.example.jetpackcomposetutorial.features.userList.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackcomposetutorial.common.data.model.UserData
import com.example.jetpackcomposetutorial.common.presentation.UserViewModel



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    onUserClick: (UserData) -> Unit,
    viewModel: UserViewModel
) {
    val userDataState = viewModel.userListLivedata.collectAsStateWithLifecycle()
    viewModel.fetchUserData()

    //val userState = viewModel.userLiveData.collectAsState()
    wrap android view
    Scaffold { it ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = userDataState.value.let{ rememberLazyListState() }
        ) {
            items(userDataState.value.size) {index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column (
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "${userDataState.value[index].first_name} ${userDataState.value[index].last_name}",
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    onUserClick(userDataState.value[index])
                                },
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                }
            }
        }
    }


}