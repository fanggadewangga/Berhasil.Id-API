package com.raion.model.response

import com.google.gson.annotations.SerializedName

data class UMKMLiteResponse (
    @field:SerializedName("umkm_id")
    val umkmId : String,

    @field:SerializedName("umkm_name")
    val umkmName : String,

    @field:SerializedName("category")
    val category : String,

    @field:SerializedName("image")
    val image : String
)