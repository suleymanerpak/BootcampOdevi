package com.example.yemekvaktii.uix.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekvaktii.data.entity.Sepet_Yemekler
import com.example.yemekvaktii.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemeklerSepetimViewModel @Inject constructor (var yrepo : YemeklerRepository) : ViewModel() {

    var sepetListesi = MutableLiveData<List<Sepet_Yemekler>>()
    var toplamFiyat = MutableLiveData<Int>()

    init {
        getCartFoods()
        Log.e("Sepetteki Yemekleri Getirme","Sepetteki Yemekleri Getirme")
    }

    fun getCartFoods()
    {
        CoroutineScope(Dispatchers.IO).launch {
            sepetListesi.value = yrepo.getCartFoods("suleyman_erpak")
            // yrepo.getCartFoods(kullanici_adi = "suleyman_erpak")
        }
    }

    fun deleteFoodFromCart(sepet_yemek_id : Int, kullanici_adi: String)
    {
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.deleteFoodFromCart(sepet_yemek_id, kullanici_adi)
            getCartFoods()
        }
        Log.e("Sepetteki Yemekleri Silme","Sepetteki Yemekleri Silme")
    }

    private fun totalFiyatiHesapla(){
        val toplam = sepetListesi.value?.sumOf { it.yemek_fiyat * it.yemek_siparis_adet }
        toplamFiyat.value = toplam ?: 0

    }
}