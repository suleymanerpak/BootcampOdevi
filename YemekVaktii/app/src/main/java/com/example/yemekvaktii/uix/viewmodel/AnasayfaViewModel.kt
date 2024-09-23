package com.example.yemekvaktii.uix.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekvaktii.data.entity.Yemekler
import com.example.yemekvaktii.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor (var yrepo : YemeklerRepository) : ViewModel(){

    var yemeklerListesi = MutableLiveData<List<Yemekler>>()


    init {
        getFoods()
        Log.e("Yemekleri Getirme","Yemekleri Getirme")

    }


    fun getFoods()
    {

        CoroutineScope(Dispatchers.Main).launch {
            yemeklerListesi.value =  yrepo.getFoods()
        }
        Log.e("Yemekleri Getirme","Yemekleri Getirme")
    }





}