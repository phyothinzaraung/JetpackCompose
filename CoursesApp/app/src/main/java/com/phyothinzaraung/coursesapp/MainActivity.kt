package com.phyothinzaraung.coursesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.phyothinzaraung.coursesapp.ui.theme.CoursesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesAppTheme {
                //Navigation System
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                ){
                    // 1 - Nav Controller
                    val navController = rememberNavController()
                    // 2 - Nav Host
                    NavHost(navController = navController,
                        startDestination = "home"){
                        //3 - Nav Graph Builder
                        composable("home"){
                            HomeScreen(onDetailsClick = {
                                title -> navController.navigate("details/title=$title")
                            }, onAboutClick = {
                                    navController.navigate("about")
                                }
                            )
                        }

                        composable("about"){
                            AboutScreen(onNavigateUp = {navController.popBackStack()})
                        }

                        composable("details/title={title}",
                            arguments = listOf(
                                navArgument("title"){
                                    type = NavType.StringType
                                    nullable = true
                                }
                            )
                        ){
                            backStackEntry ->
                                val arguments = requireNotNull(backStackEntry.arguments)

                            val title = arguments.getString("title")

                            DetailsScreen(title = title, onNavigateUp = {
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }
        }
    }
}

//1 - Home Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onDetailsClick: (title: String) -> Unit,
    onAboutClick: () -> Unit
){
    Scaffold {padding ->
        LazyColumn(contentPadding = padding){
            item{
                HomeAppBar(onAboutClick)
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
            items(allCourses){
                course -> CourseCard(
                    course,
                    onClick = {onDetailsClick(course.title)}
                )
            }
        }

    }
}

@Composable
private fun HomeAppBar(onAboutClick: () -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(text = "My Udemy Courses", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onAboutClick) {
            Text(text = "About", fontSize = 18.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCard(course: Course, onClick: () -> Unit){
    Card (
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth(),
        onClick = onClick
    ){
        Column {
            Image(
                painter = painterResource(id = course.thumbnail),
                contentDescription =course.title,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
            ) {
                Text(text = course.title)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = course.body, maxLines = 1, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

//AboutScreen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onNavigateUp: ()-> Unit){
    Scaffold {
        padding -> Column(modifier = Modifier.padding(padding)) {
        AppBar(title = "About", onNavigateUp)
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "This app is the demonstration about the navigation.")
            Spacer(modifier = Modifier.height(20.dp))
            val udemyLink = LocalUriHandler.current
            Button(onClick = { udemyLink.openUri("https://www.udemy.com/home/my-courses/learning/") }) {
                Text(text = "View our udemy course")
            }
        }
    }
    }
}

@Composable
fun AppBar(title: String, onNavigateUp: () -> Unit){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        IconButton(onClick = onNavigateUp) {
            Icon(Icons.Rounded.ArrowBack, contentDescription = "go back")
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = title, fontSize = 24.sp)
    }
}

//Detail Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(title: String?, onNavigateUp: () -> Unit){
    //searching for the correct course
    val course = allCourses.first{it.title == title}

    Scaffold { paddingValues ->
        Column(
            Modifier.padding(paddingValues)
        ) {
            IconButton(onClick = onNavigateUp) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "go back")
            }

            Image(
                painter = painterResource(
                    id = course.thumbnail
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                Text(text = course.title, fontSize = 24.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = course.body,
                    Modifier.fillMaxSize(),
                    fontSize = 20.sp
                )
            }
        }
    }
}

