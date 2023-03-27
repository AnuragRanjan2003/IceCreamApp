package com.example.icecream.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.icecream.ui.theme.IceCreamTheme

@Composable
fun HomeScreen(onClick: (t: String) -> Unit) {
    IceCreamTheme {
        var text by remember { mutableStateOf("") }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "home screen",
                    fontSize = 18.sp,
                )
                Box(modifier = Modifier.wrapContentSize()) {
                    Column {


                        TextField(
                            value = text,
                            onValueChange = { text = it },
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            modifier = Modifier
                                .fillMaxHeight(.1f)
                                .fillMaxWidth(.7f),
                            onClick = { onClick(text) },
                        ) {
                            Text(text = "go")
                        }
                    }
                }


            }

        }
    }
}


@Preview
@Composable
fun Preview() {
    IceCreamTheme {
        HomeScreen {}
    }
}