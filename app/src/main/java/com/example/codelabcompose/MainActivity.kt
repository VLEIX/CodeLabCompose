package com.example.codelabcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codelabcompose.ui.theme.CodeLabComposeTheme
import com.example.codelabcompose.ui.theme.CodeLabComposeThemeCodeLab
import com.example.codelabcompose.ui.theme.purple200
import com.example.codelabcompose.ui.theme.purple700

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    CodeLabComposeThemeCodeLab {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected = remember { mutableStateOf(false) }
    val backgroundColor = animateAsState(if (isSelected.value) Color.Red else Color.Transparent)

    Surface(color = purple200, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Hello $name!",
            color = Color.White,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .background(color = backgroundColor.value)
                .clickable(onClick = { isSelected.value = !isSelected.value })
        )
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = listOf("Android", "iOS", "Flutter")) {
    val counterState = remember { mutableStateOf(0) }

    /*
    Column(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.weight(1f)) {
            names.forEach {
                Greeting(it)
                Divider(color = Color.Black)
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            Divider(color = Color.Transparent, thickness = 32.dp)
            Counter(counterState.value, updateCount = {
                counterState.value = it
            })
        }
    }
     */

    NameList(names = List(1000) {"hola $it"})
}

@Preview("text preview")
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}