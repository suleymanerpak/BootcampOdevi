package com.example.yemekvaktii.uix.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yemekvaktii.data.entity.Yemekler
import com.example.yemekvaktii.uix.viewmodel.AnasayfaViewModel
import com.example.yemekvaktii.uix.viewmodel.YemeklerDetayViewModel
import com.example.yemekvaktii.uix.viewmodel.YemeklerSepetimViewModel
import com.google.gson.Gson

@Composable
fun SayfaGecisleri(anasayfaViewModel: AnasayfaViewModel,
                   yemeklerDetayViewModel: YemeklerDetayViewModel,
                   yemeklerSepetimViewModel: YemeklerSepetimViewModel
){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "anasayfa"){
        composable("anasayfa"){
            Anasayfa(navController,anasayfaViewModel)
        }

        composable("yemeklerDetaySayfa/{yemek}",
            arguments = listOf(
                navArgument("yemek") {type = NavType.StringType}
            )
        ){
            val json = it.arguments?.getString("yemek")
            val nesne = Gson().fromJson(json, Yemekler::class.java)
            YemeklerDetaySayfa(nesne,navController,yemeklerDetayViewModel)
        }

        composable("yemeklerSepetimSayfa/{yemek}",
            arguments = listOf(
                navArgument("yemek") {type = NavType.StringType}
            )
        ){
            val json = it.arguments?.getString("yemek")
            val nesne = Gson().fromJson(json,Yemekler::class.java)
            YemeklerSepetimSayfa(nesne,yemeklerSepetimViewModel)
        }

    }

}