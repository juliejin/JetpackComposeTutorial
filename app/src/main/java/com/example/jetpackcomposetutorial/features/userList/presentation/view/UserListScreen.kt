package com.example.jetpackcomposetutorial.features.userList.presentation.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.jetpackcomposetutorial.MainActivity
import com.example.jetpackcomposetutorial.common.data.model.UserData
import com.example.jetpackcomposetutorial.common.presentation.UserViewModel



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserListScreen(
    onUserClick: (UserData) -> Unit,
    viewModel: UserViewModel = hiltViewModel(LocalContext.current as MainActivity)
) {

    /**
     * There is no need to remember userListLivedata here because collectAsState remembers
     * all the necessary data holders under the hood.
     * Under the hood implementation of collectAsState:
     * @Composable
     * fun<T> StateFlow<T>.collectAsState() : State<T> {
     *     val state = remember { mutableStateOf(value) }
     *     LaunchedEffect(Unit) {
     *         collect {
     *             state.value = it
     *         }
     *     }
     *     return state
     * }
     */
    val userDataState = viewModel.userListLivedata.collectAsStateWithLifecycle()
    viewModel.fetchUserData()

    val userData = viewModel.userLiveData.collectAsStateWithLifecycle()

    //replace with remember and you will find if you rotate screen it will dismiss bottomsheet
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    Scaffold(
    ) { it ->
        /**
         * Example of State Hoisting, where we pass a onclick handler to BottomSheet composable
         * and make it stateless
         */
        BottomSheet(showBottomSheet = showBottomSheet, userData.value) {
            showBottomSheet = it
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = userDataState.let{ rememberLazyListState() }
        ) {
            items(userDataState.value.size) {index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Row (
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AsyncImage(
                            modifier = Modifier.width(50.dp).height(50.dp),
                            model = userDataState.value[index].icon_url,
                            contentDescription = "user image",
                            contentScale = ContentScale.Fit
                        )
                        Text(
                            text = "${userDataState.value[index].first_name} ${userDataState.value[index].last_name}",
                            modifier = Modifier
                                .clickable {
                                    //those 2 lines launches bottom sheet with user first name on it
                                    //viewModel.getUserDataById(userDataState.value[index].id.toString())
                                    //showBottomSheet = true
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