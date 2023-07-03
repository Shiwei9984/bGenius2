package com.example.unit1practicecomposequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit1practicecomposequadrant.ui.theme.Unit1PracticeComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit1PracticeComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    displayInfoCard()
                }
            }
        }
    }
}
@Composable
fun displayInfoCard(){
    Column(Modifier.fillMaxWidth()){
        Row(Modifier.weight(1f)) {
            InfoCard(
                title = stringResource(R.string.textComposable),
                description = stringResource(R.string.textDescription),
                backgroundColor = colorResource(R.color.text),
                modifier = Modifier.weight(1f)
            )
            InfoCard(
                title = stringResource(R.string.imageComposable),
                description = stringResource(R.string.imageDescription),
                backgroundColor = colorResource(R.color.image),
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            InfoCard(
                title = stringResource(R.string.rowComposable),
                description = stringResource(R.string.rowDescription),
                backgroundColor = colorResource(R.color.row),
                modifier = Modifier.weight(1f)
            )
            InfoCard(
                title = stringResource(R.string.columnComposable),
                description = stringResource(R.string.columnDescription),
                backgroundColor = colorResource(R.color.column),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
@Composable
fun InfoCard(
    title:String,
    description:String,
    backgroundColor:Color,
    modifier:Modifier=Modifier
) {
    Column(
        modifier= modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = title,
            modifier = Modifier.padding(bottom=16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InfoCardPreview() {
    Unit1PracticeComposeQuadrantTheme {
        displayInfoCard()
    }
}