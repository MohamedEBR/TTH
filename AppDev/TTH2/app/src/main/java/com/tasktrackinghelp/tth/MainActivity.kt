package com.tasktrackinghelp.tth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tasktrackinghelp.tth.ui.theme.PopupTheme
import com.tasktrackinghelp.tth.ui.theme.TTHTheme


class MainActivity : ComponentActivity()   {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TTHTheme {
                // A surface container using the 'background' color from the theme
                PopupTheme {
                    BottomNavigationBar(viewModel)                }
            }
        }
    }
}



@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "TTH preview"
)
@Composable
fun GreetingPreview() {
    val viewModel = MainViewModel() // Create an instance of your ViewModel
    TTHTheme {
        PopupTheme {
BottomNavigationBar(viewModel)        }
    }
}
