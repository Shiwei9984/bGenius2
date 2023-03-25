package com.example.unit1happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.unit1happybirthday.ui.theme.Unit1HappyBirthdayTheme
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit1HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   BirthdayGreetingWithImage(message = "Happy Birthday Shiwei!", from = "-from Kevin")
                }
            }
        }
    }
}

@Composable
fun BirthdayGreetingWithImage(message:String, from:String, modifier:Modifier=Modifier)
{
    val image = painterResource(R.drawable.androidparty)
    Box {
        Image(
            painter = image ,
        contentDescription = null,
            contentScale = ContentScale.Crop
        )
        BirthdayGreetingWithText(message = "Happy Birthday Shiwei!", from = "-from Kevin" )
    }
}

@Composable
fun BirthdayGreetingWithText(message: String,from:String,modifier:Modifier = Modifier)
{
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = message,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(top = 16.dp)
        )
        Text(
            text = from,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    Unit1HappyBirthdayTheme {
        BirthdayGreetingWithImage(message = "Happy Birthday Shiwei!", from = "-from Kevin")
    }
}