package com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.presentation.features.userList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetpackcomposetutorial.common.data.model.UserData

@Composable
fun FAB(onClick: (value: Boolean)->Unit) {
    ExtendedFloatingActionButton(
        text = { androidx.compose.material3.Text("Show bottom sheet") },
        icon = { Icon(Icons.Filled.Add, contentDescription = "") },
        onClick = {
            onClick(true)
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(showBottomSheet: Boolean,
                userData: UserData?,
                onBottomSheetStateValueChange: (value: Boolean)->Unit) {
    val sheetState = rememberModalBottomSheetState()
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                onBottomSheetStateValueChange(false)
            },
            sheetState = sheetState
        ) {
            Text("Bottom Sheet")
            RenderBottomSheet(userData)
        }
    }
}

@Composable
fun RenderBottomSheet(
    userData: UserData?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.98f)
    ) {
        Text(text = "First Name: ${userData?.first_name}",
            style = MaterialTheme.typography.displayMedium)
    }
}