package com.phyothinzaraung.navapp

sealed class Destinations(val route: String){
    object FirstScreen: Destinations("First Screen")
    object SecondScreen: Destinations("Second Screen")
    object ThirdScreen: Destinations("Third Screen")
}
