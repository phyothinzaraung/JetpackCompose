package com.phyothinzaraung.myjetpackcomposeapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phyothinzaraung.myjetpackcomposeapp.ui.theme.MyJetpackComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJetpackComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(name = "Android")
//                    Column {
//                        Greeting("Android")
//                        DisplayText()
//                        DisplayUserList()
//                    }
                }
            }
        }
    }
}

@Composable
fun DisplayText(){
    Text(text = stringResource(id = R.string.welcome_msg))
}

@Composable
fun Greeting(name: String) {
//    Column(modifier = Modifier
//        .background(Color.Gray)
//        .clickable {Log.d("Click", "Here")}) {
//        Text(
//            text = "Hello $name!",
//            modifier = Modifier.background(Color.Green)
//                .fillMaxSize()
//        )
//    }

//    Text(
//        modifier = Modifier.verticalScroll(state = rememberScrollState()),
//        text = stringResource(id = R.string.paragraph_text),
//        fontSize = 36.sp
//        )

//    Column(horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center) {
//        Text(text = "Hello John")
//        Text(text = "Hello Jack")
//    }
    val context = LocalContext.current.applicationContext
    Button(onClick = {
        Toast.makeText(context, "button clicked", Toast.LENGTH_SHORT).show()
    },
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = "click Me")
    }

}

@Composable
fun DisplayUserList(){
    val users = listOf<String>("user1", "user2", "user3")
    for (user in users){
        Text(
            text = "Hello $user",
            color = Color.Blue,
            fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyJetpackComposeAppTheme {
        Greeting("Android")
        DisplayText()
    }
}