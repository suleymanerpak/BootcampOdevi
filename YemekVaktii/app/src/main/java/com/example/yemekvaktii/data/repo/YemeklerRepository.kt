package com.example.yemekvaktii.data.repo

import com.example.yemekvaktii.data.datasource.YemeklerDataSource

class YemeklerRepository(var yds: YemeklerDataSource) {

    suspend fun getFoods()  = yds.getFoods()

    suspend fun addFoodToCart(yemek_adi: String, yemek_resim_adi :String,yemek_fiyat : Int,yemek_siparis_adet : Int,kullanici_adi:String)
            = yds.addFoodToCart(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    suspend fun getCartFoods(kullanici_adi: String)  = yds.getCartFoods(kullanici_adi)

    suspend fun deleteFoodFromCart(sepet_yemek_id : Int, kullanici_adi: String) = yds.deleteFoodFromCart(sepet_yemek_id, kullanici_adi)


}