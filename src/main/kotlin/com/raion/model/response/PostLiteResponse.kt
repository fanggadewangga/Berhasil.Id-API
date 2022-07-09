package com.raion.model.response

import com.google.gson.annotations.SerializedName

data class PostLiteResponse (
    @field:SerializedName("post_id")
    val postId : String,

    @field:SerializedName("title")
    val title : String,

    @field:SerializedName("image")
    val image : String,

    @field:SerializedName("caption")
    val caption : String
)