package com.tasktrackinghelp.tth

sealed class Screens(val route : String) {
    object Home : Screens("home_route")
    object Calendar : Screens("calendar")
    object Settings : Screens("setting")
}