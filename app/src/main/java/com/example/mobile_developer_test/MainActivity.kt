//Marwah Nur Shafira
package com.example.mobile_developer_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_developer_test.ui.theme.MobileDeveloperTestTheme
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.mobile_developer_test.ui.theme.poppinsFontFamily
import com.example.mobile_developer_test.view.MainActivity2
import com.example.mobile_developer_test.view.MainActivity3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileDeveloperTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FirstScreen(
                        name = "",
                        palindrome = "",
                        textBtn1 = stringResource(id = R.string.check),
                        textBtn2 = stringResource(id = R.string.next)
                    )
                }
            }
        }
    }
}

@Composable
fun StyleTextField(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                placeholder,
                color = Color(0xFF686777),
                fontSize = 16.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.W500
            )},
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFE2E3E4),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = Color(0xFF686777),
            fontSize = 16.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W500
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun GroupTextField(username: String, sentence: String, onNameChange: (String) -> Unit, onSentenceChange: (String) -> Unit) {
    Column {
        StyleTextField(
            value = username,
            onValueChange = onNameChange,
            placeholder = stringResource(R.string.name)
        )
        Spacer(modifier = Modifier.height(20.dp))
        StyleTextField(
            value = sentence,
            onValueChange = onSentenceChange,
            placeholder = stringResource(id = R.string.palindrome)
        )
    }
}

@Composable
fun StyleButton(textBtn: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2B637B),
            contentColor = Color.White
        ),
        onClick = onClick
    ) {
        Text(
            text = textBtn,
            fontSize = 14.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W500
        )
    }
}

@Composable
fun GroupButton(textBtn1: String, textBtn2: String, onFirstButtonClick: () -> Unit, onSecondButtonClick: () -> Unit) {
    Column {
        StyleButton(textBtn1, onClick = onFirstButtonClick)
        Spacer(modifier = Modifier.height(5.dp))
        StyleButton(textBtn2, onClick = onSecondButtonClick)
    }
}

@Composable
fun ImageIcon() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable._2f7c87c9837957ea28a7fca1c27c7ab),
            contentDescription = "icon",
            modifier = Modifier.size(128.dp)
        )
    }
}

@Composable
fun ComposableFirstScreen(
    textBtn1: String,
    textBtn2: String,
    username: String,
    sentence: String,
    onNameChange: (String) -> Unit,
    onSentenceChange: (String) -> Unit,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center
    ) {
        ImageIcon()
        Spacer(modifier = Modifier.height(50.dp))
        GroupTextField(username, sentence, onNameChange, onSentenceChange)
        Spacer(modifier = Modifier.height(50.dp))
        GroupButton(textBtn1, textBtn2, onFirstButtonClick, onSecondButtonClick)
    }
}

@Composable
fun FirstScreen(
    name: String,
    palindrome: String,
    textBtn1: String,
    textBtn2: String,
) {
    var username by remember { mutableStateOf(name) }
    var sentence by remember { mutableStateOf(palindrome) }
    var showDialog by remember { mutableStateOf(false) }
    var isPalindrome by remember { mutableStateOf(false) }
    val context = LocalContext.current

    fun checkPalindrome(text: String): Boolean {
        val cleanedText = text.replace("\\s+".toRegex(), "").lowercase()
        return cleanedText == cleanedText.reversed()
    }

    fun onCheckClick() {
        isPalindrome = checkPalindrome(sentence)
        showDialog = true
    }

    fun onNextClick() {
        val intentForMainActivity2 = Intent(context, MainActivity2::class.java)
        intentForMainActivity2.putExtra("USERNAME", username)
        context.startActivity(intentForMainActivity2)

        val intentForMainActivity3 = Intent(context, MainActivity3::class.java)
        intentForMainActivity3.putExtra("USERNAME", username)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Palindrome Check") },
            text = {
                Text(
                    text = if (isPalindrome) "isPalindrome" else "not palindrome",
                    fontSize = 20.sp
                )
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable._ea2a92c2d7771c7921246067122c2e9),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
        )
        ComposableFirstScreen(
            textBtn1 = textBtn1,
            textBtn2 = textBtn2,
            username = username,
            sentence = sentence,
            onNameChange = { username = it },
            onSentenceChange = { sentence = it },
            onFirstButtonClick = { onCheckClick() },
            onSecondButtonClick = { onNextClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MobileDeveloperTestTheme {
        FirstScreen(
            name = "",
            palindrome = "",
            textBtn1 = stringResource(id = R.string.check),
            textBtn2 = stringResource(id = R.string.next)
        )
    }
}