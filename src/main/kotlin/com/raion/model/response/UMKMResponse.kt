package com.raion.model.response

import com.google.gson.annotations.SerializedName

data class UMKMResponse(

    @field:SerializedName("umkm_id")
    val umkmId: String,

    @field:SerializedName("umkm_name")
    val umkmName: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("sell_time")
    val sellTime: Int,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("item_sold")
    val itemSold: Int,

    @field:SerializedName("reviews")
    val reviews: List<ReviewResponse>,

    @field:SerializedName("posts")
    val posts: List<PostLiteResponse>
)
