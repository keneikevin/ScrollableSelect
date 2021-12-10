package com.example.scrollableselect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.scrollableselect.ui.theme.ScrollableSelectTheme

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrollableselect.ui.theme.Blue100
import com.example.scrollableselect.ui.theme.LightGray100
import com.jetpack.scrollableselectlayout.ScrollableSelectLayout
import com.jetpack.scrollableselectlayout.rememberScrollableSelectState

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableSelectTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    InitDensity()
                    ScrollableLayout()
                }
            }
        }
    }
}
data class Symptom(
    val ID: Int,
    val Name: String
)

@ExperimentalMaterialApi
@Composable
fun ScrollableLayout() {
    val context = LocalContext.current
    val items = remember {
        mutableListOf(
            Symptom(1,"Mango"),
            Symptom(2,"Apple"),
            Symptom(3,"Dates"),
            Symptom(4,"Cherries"),
            Symptom(5,"EffFruit"),
            Symptom(6,"Malaria"),
            Symptom(7,"HackBerry"),
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Custom Popup Spinner",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Blue100),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Select Fruit Name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
                val scrollableSelectState = rememberScrollableSelectState()
                ScrollableSelectLayout(
                    items = items,
                    itemHeight = 50.dp,
                    scrollableSelectState = scrollableSelectState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    visibleAmount = 5
                ) { item, selected ->
                    Text(
                        text = item.Name,
                        color = if (selected) Blue100 else LightGray100,
                        fontWeight = W500,
                        style = MaterialTheme.typography.body1
                    )
                }
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Blue100)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Button(
                        onClick = {
                            Toast.makeText(context, items[scrollableSelectState.currentSwipeItemIndex].ID.toString(), Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Blue100),
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text(text = "Ok", color = Color.White)
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(Color.White)
                    )
                    Button(
                        onClick = {
                            Toast.makeText(context, "Select Item Cancel", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Blue100),
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text(text = "Cancel", color = Color.White)
                    }
                }
            }
        }
    }
}


































@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScrollableLayout()
}