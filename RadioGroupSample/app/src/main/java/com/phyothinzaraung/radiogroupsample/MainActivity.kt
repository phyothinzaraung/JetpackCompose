package com.phyothinzaraung.radiogroupsample

import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phyothinzaraung.radiogroupsample.ui.theme.RadioGroupSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RadioGroupSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    //MyRadioButton()
                    //MySwitch()
                    //MyCheckBox()
                   //MyCheckBoxList()
                    //MyCircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun MyRadioButton(){
    val context = LocalContext.current

    val radioOptions = listOf<String>("Pizza", "Pasta", "Fish", "Meat")

    var (selectedItem, onOptionsSelected) = remember {
        mutableStateOf(radioOptions[0])
    }

    Column(modifier = Modifier.selectableGroup()) {
        radioOptions.forEach { item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .selectable(
                    selected = (selectedItem == item),
                    onClick = { onOptionsSelected(item) },
                    role = Role.RadioButton
                )
                .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = {onOptionsSelected(item)},
                    selected = (selectedItem == item)
                )
                Text(text = item)
            }
        }
    }
}

@Composable
fun MySwitch(){

    var switchOn by remember {
        mutableStateOf(false)
    }

    Row {
        Switch(
            checked = switchOn,
            onCheckedChange = {switchOn = it},
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                checkedTrackColor = Color.Red
            )
        )
        Text(text = "Night Mode")
    }
}

@Composable
fun MyCheckBox(){

    var checkedState by remember {
        mutableStateOf(false)
    }

    Row {
        Checkbox(
            checked = checkedState,
            onCheckedChange = {checkedState = it}
        )
        Text(text = "Check Box Testing")
    }

}

@Composable
fun MyCheckBoxList(){
    val toppings = listOf<String>("cheese", "tomato", "spicy")

    val ctx = LocalContext.current.applicationContext

    Column(
        horizontalAlignment = Alignment.Start
    ) {
        toppings.forEach {
            topping ->
            var checked by remember {
                mutableStateOf(false)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = {checked = it}
                )
                Text(text = topping)
            }
        }
    }
}

@Composable
fun MyCircularProgressIndicator(){

    //Indeterminate Circulator
//    CircularProgressIndicator(
//        modifier = Modifier.size(size = 32.dp),
//        color = Color.Red,
//        strokeWidth = 6.dp
//    )

    //Determinate circular indicator
    CircularProgressIndicator(
        progress = 0.7f,
        modifier = Modifier.size(size = 32.dp),
        color = Color.Red,
        strokeWidth = 6.dp)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RadioGroupSampleTheme {
        Greeting("Android")
    }
}