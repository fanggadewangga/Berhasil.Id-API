package com.raion.model.body

import com.google.gson.annotations.SerializedName

data class PostBody(
    @field:SerializedName("title")
    val title : String,

    @field:SerializedName("image")
    val image : String,

    @field:SerializedName("caption")
    val caption : String
)
