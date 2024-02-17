package com.tasktrackinghelp.tth

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tasktrackinghelp.tth.ui.theme.PopupTheme
import com.tasktrackinghelp.tth.ui.theme.TTHTheme

@Composable
fun SettingsScreen(navController: NavController) {
    TTHTheme {
        PopupTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val context = LocalContext.current

                ClickableText(
                        text = AnnotatedString("Help"),
                          style = MaterialTheme.typography.titleLarge.copy(
                        textDecoration = TextDecoration.Underline,
                              color = MaterialTheme.colorScheme.primary,
                              fontWeight = FontWeight.Bold,


                    ),
                        modifier = Modifier.padding(vertical = 20.dp),
                        onClick = {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://userinstructionweb.cm-10kj-909263.repl.co/")))
                        }
                    )
                }
            }
        }
    }
