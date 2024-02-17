package com.tasktrackinghelp.tth

import java.time.LocalDateTime

data class Event(
    val name: String,
    val color: androidx.compose.ui.graphics.Color,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val description: String? = null,
)
