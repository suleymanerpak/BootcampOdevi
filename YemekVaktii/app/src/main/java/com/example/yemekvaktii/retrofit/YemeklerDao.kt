package com.example.yemekvaktii.retrofit

import com.example.yemekvaktii.data.entity.CRUDCevap
import com.example.yemekvaktii.data.entity.Sepet_YemeklerCevap
import com.example.yemekvaktii.data.entity.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {

    // http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getFoods() : YemeklerCevap

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addFoodToCart(@Field("yemek_adi") yemek_adi : String,
                              @Field("yemek_resim_adi") yemek_resim_adi : String,
                              @Field("yemek_fiyat") yemek_fiyat : Int,
                              @Field("yemek_siparis_adet") yemek_siparis_adet : Int,
                              @Field("kullanici_adi") kullanici_adi : String) : CRUDCevap

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getCartFoods(@Field("kullanici_adi") kullanici_adi: String) : Sepet_YemeklerCevap



    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun deleteFoodFromCart(@Field("sepet_yemek_id") sepet_yemek_id : Int,
                                   @Field("kullanici_adi") kullanici_adi: String) :CRUDCevap

}