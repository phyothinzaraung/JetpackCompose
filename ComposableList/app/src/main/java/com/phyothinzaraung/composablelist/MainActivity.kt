package com.phyothinzaraung.composablelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phyothinzaraung.composablelist.ui.theme.ComposableListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var osList = listOf<String>("Android", "IOS", "Window", "Linux", "MacOS", "Unix")
                    PopulateGrid(osList = osList)
                    //PopulateList(osList = osList)
                }
            }
        }
    }
}

@Composable
fun PopulateList(osList: List<String>){
    LazyRow(){
        //Adding single item
        item { 
            Text(text = "Your first item")
        }
        
        //adding multiple items
        items(5){
            Text(text = "Item $it")
        }
        
        //populating list of items
        items(osList){
            Text(text = "OS Name: $it")
        }
    }
}

@Composable
fun CreateRowItem(osName: String){
    Row {
        Text(
            text = osName,
            fontSize = 32.sp
        )
    }
}

@Composable
fun PopulateGrid(osList: List<String>){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        content = {
            items(osList.size){
                index ->  Card {
                Text(text = osList[index])
            }
            }
        }
    )
}