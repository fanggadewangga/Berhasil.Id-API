package com.raion.model.body

import com.google.gson.annotations.SerializedName

data class UMKMBody(
    @field:SerializedName("umkmName")
    val umkmName : String,

    @field:SerializedName("description")
    val description : String,

    @field:SerializedName("address")
    val address : String,

    @field:SerializedName("category")
    val category : String,

    @field:SerializedName("sell_time")
    val sellTime : Int,

    @field:SerializedName("image")
    val image : String,

    @field:SerializedName("item_sold")
    val itemSold : Int
)
