//Marwah Nur Shafira
package com.example.mobile_developer_test.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile_developer_test.ui.theme.MobileDeveloperTestTheme
import coil.compose.rememberImagePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mobile_developer_test.R
import com.example.mobile_developer_test.controller.UsersViewModel
import com.example.mobile_developer_test.model.User
import com.example.mobile_developer_test.ui.theme.poppinsFontFamily
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("USERNAME") ?: "Guest"
        setContent {
            MobileDeveloperTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserListScreen(username)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(username: String, usersViewModel: UsersViewModel = viewModel()) {
    val users by usersViewModel.users.collectAsState(emptyList())
    val isLoading by usersViewModel.isLoading.collectAsState()
    val context = LocalContext.current
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    Scaffold(
        topBar = {
            TopAppBar(stringResource(id = R.string.third_screen))
        }
    ) { innerPadding ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { usersViewModel.loadNextPage() }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
                    .padding(horizontal = 20.dp),
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (users.isEmpty() && !isLoading) {
                    item {
                        EmptyContent()
                    }
                } else {
                    items(users) { user ->
                        UserItem(user) { selectedUsername ->
                            val intent = Intent(context, MainActivity2::class.java).apply {
                                putExtra("USERNAME", username)
                                putExtra("SELECTED_USERNAME", selectedUsername)
                            }
                            context.startActivity(intent)
                        }
                        Divider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color(0xFFE2E3E4)
                        )
                    }
                    if (isLoading) {
                        item {
                            LoadingItem()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyContent() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(text = "No users found", fontSize = 16.sp)
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun UserItem(user: User, onClick: (String) -> Unit) {
    val imageModifier = Modifier
        .size(64.dp)
        .clip(CircleShape)

    Column(
        modifier = Modifier
            .clickable { onClick(user.first_name + " " + user.last_name) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(user.avatar),
                contentDescription = null,
                modifier = imageModifier
            )
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${user.first_name} ${user.last_name}",
                    color = Color(0xFF04021D),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp
                )
                Text(
                    text = user.email,
                    color = Color(0xFF686777),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserList() {
    UserListScreen(username = "")
}