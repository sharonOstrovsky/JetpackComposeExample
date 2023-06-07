package com.example.jetpackcompose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ViewContainer()
                }
            }


        }
    }
}

@Preview
@Composable
fun ViewContainer() {
    Scaffold(
        topBar = { Toolbar() },
        content = { Content() },
        floatingActionButton = { FAB() },
        floatingActionButtonPosition = FabPosition.End
    )
}

@Composable
fun FAB() {
    val contex = LocalContext.current
    FloatingActionButton(onClick = {
        Toast.makeText(contex, "Contratame", Toast.LENGTH_SHORT).show()
    }) {
        Text(text = "X")
    }
}


@Composable
fun Toolbar() {
    TopAppBar(
        title = { Text(text = "Sharon Profile", color = Color.White) },
        backgroundColor = colorResource(id = R.color.backgroud)
    )
}


@Composable
fun Content() {

    var counter by rememberSaveable {
        mutableStateOf(0)
    }

    var show by rememberSaveable {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        item {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                painter = painterResource(id = R.drawable.foto7),
                contentDescription = "foto personal"
            )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_favorite_24),
                    contentDescription = "like",
                    modifier = Modifier.clickable { counter++ }
                )
                Text(
                    text = counter.toString(),
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Text(
                text = "Sharon Ostrovsky", fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Android Dev",
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                item {
                    Text(text = "Stack:", color = Color.White)
                    Divider(Modifier.width(4.dp))
                    Text(text = "Java", color = Color.White)
                    Divider(Modifier.width(4.dp))
                    Text(text = "Kotlin", color = Color.White)
                }
            }
            LazyRow(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                item {
                    Text(
                        text = "probando scroll !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!",
                        color = Color.White
                    )
                }
            }

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = { show = true }) {
                    Text(text = "CONTACTO")
                }
            }

            MyDialog(show, {show = false}, { Log.i("confirm", "click")})

        }
    }
}


@Composable
fun MyDialog(show : Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {

    if(show){
        AlertDialog(onDismissRequest = { onDismiss() },
            confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(text = "ConfirmButton")

            }
        },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "DismissButton")

                }
            },
            title = { Text(text = "Contactame") },
            text = { Text(text = "https://www.linkedin.com/in/sharon-ostrovsky/") })


    }



}