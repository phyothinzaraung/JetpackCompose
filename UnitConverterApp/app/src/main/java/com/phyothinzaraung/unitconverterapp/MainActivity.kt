package com.phyothinzaraung.unitconverterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phyothinzaraung.unitconverterapp.ui.theme.UnitConverterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    //var myViewModel: MyViewModel = MyViewModel()
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(myViewModel: MyViewModel = viewModel()){

    var inputTemp by remember {
        mutableStateOf("0")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Unit Converter App",
            fontSize = 32.sp)

        OutlinedTextField(
            value = inputTemp,
            onValueChange = {newTemp: String -> inputTemp = newTemp},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            label = { Text(text = "Enter temperature in Fahrenheit")},
            singleLine = true
        )

        Button(onClick = { myViewModel.convertTemperature(inputTemp) }) {
            Text(text = "Convert Now")
        }

        Text(text = "The Degee in Celsius:${myViewModel.tempC}", fontSize = 28.sp)
    }
}

