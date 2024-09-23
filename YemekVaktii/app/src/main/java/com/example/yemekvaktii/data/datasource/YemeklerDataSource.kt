package com.example.yemekvaktii.data.datasource

import com.example.yemekvaktii.data.entity.Sepet_Yemekler
import com.example.yemekvaktii.data.entity.Yemekler
import com.example.yemekvaktii.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var ydao : YemeklerDao) {

    suspend fun getFoods() : List<Yemekler> = withContext(Dispatchers.IO){

        return@withContext ydao.getFoods().yemekler
    }

    suspend fun addFoodToCart(yemek_adi: String, yemek_resim_adi :String,yemek_fiyat : Int,yemek_siparis_adet : Int,kullanici_adi:String){
        ydao.addFoodToCart(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    }

    suspend fun getCartFoods(kullanici_adi: String) : List<Sepet_Yemekler> =
        withContext(Dispatchers.IO)
        {
            return@withContext ydao.getCartFoods(kullanici_adi).sepet_yemekler
        }

    suspend fun deleteFoodFromCart(sepet_yemek_id : Int, kullanici_adi: String){
        ydao.deleteFoodFromCart(sepet_yemek_id,kullanici_adi)
    }


}