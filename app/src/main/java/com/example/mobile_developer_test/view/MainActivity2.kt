//Marwah Nur Shafira
@file:Suppress("DEPRECATION")

package com.example.mobile_developer_test.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_developer_test.R
import com.example.mobile_developer_test.StyleButton
import com.example.mobile_developer_test.ui.theme.MobileDeveloperTestTheme
import com.example.mobile_developer_test.ui.theme.poppinsFontFamily

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("USERNAME") ?: "Guest"
        val usernameSelected = intent.getStringExtra("SELECTED_USERNAME") ?: "Selected User Name"

        setContent {
            MobileDeveloperTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SecondScreen(username, usernameSelected)
                }
            }
        }
    }
}

@Composable
fun Profile(username: String) {
    Column {
        Text(
            text = stringResource(id = R.string.welcome),
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            color = Color(0xFF04021D)
        )
        Text(
            text = username,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W600,
            fontSize = 18.sp,
            color = Color(0xFF04021D)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(username: String, selectedUsername: String) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(stringResource(id = R.string.second_screen))
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 30.dp)
                .padding(horizontal = 20.dp),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                Profile(username)
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = selectedUsername,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.W600,
                        fontSize = 24.sp,
                        color = Color(0xFF04021D)
                    )
                }
            }
            item {
                StyleButton(stringResource(id = R.string.choose_a_user)) {
                    val intent = Intent(context, MainActivity3::class.java)
                    intent.putExtra("USERNAME", username)
                    context.startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun TopAppBar(title: String) {
    val context = LocalContext.current

    CenterAlignedTopAppBar(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFE2E3E4),
            ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                        contentDescription = "arrow back",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                (context as? ComponentActivity)?.onBackPressed()
                            }
                    )
                }
                Text(
                    text = title,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MobileDeveloperTestTheme {
        SecondScreen("", "")
    }
}