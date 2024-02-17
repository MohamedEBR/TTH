package com.tasktrackinghelp.tth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tasktrackinghelp.tth.ui.theme.PopupTheme
import com.tasktrackinghelp.tth.ui.theme.TTHTheme

@Composable
fun Header(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFA84166),
                shape = RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "Welcome",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFf7eae8)
        )
        Spacer(modifier = Modifier.padding(10.dp))

    }
}

@Composable
fun Body() {
    Box {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Tasks of the Week",
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .padding(top = 18.dp),
                fontSize = 24.sp,
                color = Color(0xFFa84165),
                fontWeight = FontWeight.SemiBold,
            )
            CalendarApp()
        }
    }
}
@Composable
fun HomeScreen(navController: NavController) {
    TTHTheme {
        PopupTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.onPrimary
            ) {
                Column {
                    Header()
                    Body()
                }
            }
        }
    }
}