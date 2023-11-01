package com.mastercoding

import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mastercoding.ui.theme.MyJetPackComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJetPackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                  //  modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyUI()

                }
            }
        }
    }

    @Composable
    fun MyUI(){
        // Top app bar: provides the info about the
        // content and actions related to the current
        // screen. (Navigation, actions, screen titles..)
        val ctx = LocalContext.current.applicationContext

        TopAppBar(
            title = {Text(text = "Our Courses")},
            navigationIcon = {
                IconButton(onClick = {
                    Toast.makeText(ctx,
                    "You Clicked the icon",
                    Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Navigation Icon" )

                }

            },

            actions = {
                // Search Icon
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search" )

                }
            },

          //  backgroundColor = Color.Red,
            elevation = 10.dp






        )




    }






    @Preview()
    @Composable
    fun DefaultPreview() {
        MyUI()
    }



}