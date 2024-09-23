package com.example.yemekvaktii.uix.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yemekvaktii.uix.viewmodel.AnasayfaViewModel
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage
import java.lang.reflect.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController, anasayfaViewModel: AnasayfaViewModel){


    val yemeklerListesi = anasayfaViewModel.yemeklerListesi.observeAsState(listOf())


    LaunchedEffect(key1 = true){
        anasayfaViewModel.getFoods()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Yemek Vakti") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.Black
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                }
            )
        },
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = androidx.compose.ui.Modifier
                .padding(12.dp)
                .padding(paddingValues)
                .fillMaxWidth()
        ) {items(yemeklerListesi.value.count()){index ->

            val yemek = yemeklerListesi.value[index]

            Card(
                modifier = androidx.compose.ui.Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        val yemekJson = Gson().toJson(yemek)
                        navController.navigate("yemeklerDetaySayfa/$yemekJson")
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent  // Arka planı transparan yapıyoruz
                ),
            ) {
                Column(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clickable {
                            val yemekJson = Gson().toJson(yemek)
                            navController.navigate("yemeklerDetaySayfa/$yemekJson")
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"

                    GlideImage(imageModel =resimUrl,
                        modifier = androidx.compose.ui.Modifier.size(250.dp,250.dp),
                        failure = {
                            // Görsel yüklenemezse burası çalışır
                            Text("Resim Yüklenemedi")
                            Log.e("Resim Yüklenemedi","Resim Yüklenemedi")
                        }
                    )

                    Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))
                    Text(
                        text = yemek.yemek_adi,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = androidx.compose.ui.Modifier.height(4.dp))
                    Text(
                        text = "${yemek.yemek_fiyat}",
                        color = Color.Blue,
                        fontSize = 20.sp,

                        )
                }
            }

        }
        }
    }


}