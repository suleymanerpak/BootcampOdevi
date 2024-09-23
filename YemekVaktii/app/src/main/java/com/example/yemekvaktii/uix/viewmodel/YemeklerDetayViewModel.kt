package com.example.yemekvaktii.uix.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.yemekvaktii.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemeklerDetayViewModel @Inject constructor (var yrepo : YemeklerRepository) : ViewModel(){

    fun addFoodToCart(yemek_adi: String, yemek_resim_adi :String,yemek_fiyat : Int,yemek_siparis_adet : Int,kullanici_adi:String)
    {
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.addFoodToCart(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }

        Log.e("Sepete Yemek Ekleme","Sepete Yemek Ekleme")
    }
}