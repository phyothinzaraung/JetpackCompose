package com.phyothinzaraung.navapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.phyothinzaraung.navapp.ui.theme.NavAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayNav()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayNav() {
    //Nav Controller
    val navController = rememberNavController()

    //NavHost
    NavHost(
        navController = navController,
        startDestination = Destinations.FirstScreen.toString()
    ){
        //NavGraphBuilder: add destination to the nav graph
        composable(route = Destinations.FirstScreen.toString()){
            FirstScreen(navController)
        }

        composable(route = Destinations.SecondScreen.toString()+"/{username}"){
            val user = it.arguments?.getString("username")
            user?.let { it1 -> SecondScreen(navController, it1) }
        }

        composable("profile/{userId}",
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
                defaultValue = ""
            })){
                val userId = it.arguments?.getString("userId")
                ThirdScreen(userId = userId!!,
                    navController = navController)
            }
    }

}

@ExperimentalMaterial3Api
@Composable
fun FirstScreen(navController: NavController){
    var username by remember {
        mutableStateOf("")
    }
    Column {
        Text(text = "Welcome from First Screen")
        TextField(
            value = username,
            onValueChange = {newName -> username = newName})
        Button(onClick = { navController.navigate(Destinations.SecondScreen.toString()+"/$username") }) {
            Text(text = "Go to Second Screen")
        }
    }

}

@Composable
fun SecondScreen(navController: NavController, user: String){
    Column {
        Text(text = "Welcome $user")
        Button(onClick = { navController.navigate("profile/77") }) {
            Text(text = "Go to Third Screen")
        }
    }

}

@Composable
fun ThirdScreen(userId: String, navController: NavController){
    Text(text = "User id is $userId")
}