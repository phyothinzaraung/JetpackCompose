package com.phyothinzaraung.creditcardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phyothinzaraung.creditcardapp.ui.theme.CreditCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreditCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayCards()
                }
            }
        }
    }
}

@Composable
fun DisplayCards(){
    val cards = listOf<CardInfo>(
        CardInfo("1234 5678 0000 1111",
            "John Smith",
            R.drawable.cardback1,
            R.drawable.visa),
        CardInfo("1234 5678 0000 2222",
            "John Smith",
            R.drawable.cardback2,
            R.drawable.mastercard),
        CardInfo("1234 5678 0000 3333",
            "John Smith",
            R.drawable.cardback3,
            R.drawable.visa),
        CardInfo("1234 5678 0000 4444",
            "John Smith",
            R.drawable.cardback4,
            R.drawable.mastercard)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(cards){
            card -> CreditCard(cardInfo = card)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CreditCardAppTheme {

    }
}