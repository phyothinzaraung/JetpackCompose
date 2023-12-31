package com.phyothinzaraung.constraintlayoutcomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.phyothinzaraung.constraintlayoutcomposable.ui.theme.ConstraintLayoutComposableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayoutComposableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintScreen()
                }
            }
        }
    }
}

@Composable
fun ConstraintScreen(){
    ConstraintLayout {
        val(redBox, blueBox, yellowBox, greenBox, blackBox, magentaBox, cyanBox) = createRefs()

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.matchParent
                height = Dimension.value(100.dp)
            }
        )

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                top.linkTo(redBox.bottom)
                end.linkTo(redBox.end)
            }
        )

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                top.linkTo(blueBox.bottom)
                end.linkTo(blueBox.end)
            })

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Green)
            .constrainAs(greenBox) {
                top.linkTo(yellowBox.bottom)
            }
        )

        Box(modifier = Modifier.size(50.dp)
            .background(Color.Black)
            .constrainAs(blackBox){
                top.linkTo(yellowBox.bottom)
            }
        )



        createHorizontalChain(greenBox, blackBox, chainStyle = ChainStyle.Packed)

        val guideline = createGuidelineFromTop(0.3f)

        Box(modifier = Modifier.size(100.dp)
            .background(Color.Magenta)
            .constrainAs(magentaBox){
                bottom.linkTo(guideline)
                end.linkTo(parent.end)

            }
        )

        Box(modifier = Modifier.size(100.dp)
            .background(Color.Cyan)
            .constrainAs(cyanBox){
                bottom.linkTo(guideline)

            }
        )
    }


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
    ConstraintLayoutComposableTheme {
        ConstraintScreen()
    }
}