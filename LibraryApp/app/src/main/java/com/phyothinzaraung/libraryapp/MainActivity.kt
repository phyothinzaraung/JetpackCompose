package com.phyothinzaraung.libraryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phyothinzaraung.libraryapp.repository.Repository
import com.phyothinzaraung.libraryapp.room.BookDB
import com.phyothinzaraung.libraryapp.room.BookEntity
import com.phyothinzaraung.libraryapp.screens.UpdateScreen
import com.phyothinzaraung.libraryapp.ui.theme.LibraryAppTheme
import com.phyothinzaraung.libraryapp.viewmodel.BookViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mContext = LocalContext.current
                    val bookDB = BookDB.getInstance(mContext)
                    val repository = Repository(bookDB)
                    val bookViewModel = BookViewModel(repository)

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "MainScreen"){
                        composable("MainScreen"){
                            MainScreen(bookViewModel = bookViewModel, navController= navController)
                        }
                        composable("UpdateScreen/{bookId}"){
                            UpdateScreen(bookViewModel, bookId = it.arguments?.getString("bookId"))
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(bookViewModel: BookViewModel, navController: NavHostController){

    var inputBook by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 22.dp, start = 6.dp, end = 6.dp)
    ) {
        Text(text = "Insert Book in Room Database", fontSize = 22.sp)
        OutlinedTextField(
            value = inputBook,
            onValueChange = {enteredText -> inputBook = enteredText},
            label = { Text(text = "Enter the Book Name")},
            placeholder = { Text(text = "Enter Your Book Name")}
        )
        Button(onClick = {
            bookViewModel.addBook(BookEntity(0, inputBook))
        },
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text(text = "Save Book")
        }
        
        BookList(viewModel = bookViewModel, navController = navController)
    }
}

@Composable
fun BookCard(viewModel: BookViewModel, book: BookEntity, navController: NavHostController){
    Card (modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()){
        Row (verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "" + book.id, 
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                color = Color.Blue
            )
            Text(
                text = book.title,
                fontSize = 24.sp,
                modifier = Modifier.fillMaxSize(0.7f),
                color = Color.Black
            )

            Row(horizontalArrangement = Arrangement.End) {
                IconButton(onClick = { viewModel.deleteBook(book) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }

                IconButton(onClick = {
                    navController.navigate("UpdateScreen/${book.id}")
                }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "edit")
                }
            }
        }
    }
}

@Composable
fun BookList(viewModel: BookViewModel, navController: NavHostController){
    val books by viewModel.books.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "My Library", fontSize = 24.sp, color = Color.Red)

        LazyColumn(){
            items(items = books){
                    book -> BookCard(
                viewModel = viewModel,
                book = book,
                navController)
            }
        }
    }
}

