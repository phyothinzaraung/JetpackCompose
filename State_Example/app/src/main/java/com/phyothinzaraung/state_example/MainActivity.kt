package com.phyothinzaraung.state_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.phyothinzaraung.state_example.ui.theme.State_ExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            State_ExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    var count by remember {
//        mutableStateOf(0)
//    }
//    Column {
//        Button(onClick = { count++ }) {
//           Text(text = "Count $count")
//        }
//    }

    var enteredText by remember {
        mutableStateOf("")
    }

    var isUserBelow18 by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.padding(50.dp)
    ) {
        TextField(
            value = enteredText,
            onValueChange = {newText -> enteredText = newText},
            label = { Text(text = "Name")},
            placeholder = { Text(text = "Enter your name")},
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "email")},
            isError = isUserBelow18,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    isUserBelow18 = validateAge(inputAge = enteredText)
                }
            )
        )
        if (isUserBelow18){
            Text(text = "Invalid Age",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp))
        }

    }
}

private fun validateAge(inputAge: String): Boolean = inputAge.toInt() < 18

