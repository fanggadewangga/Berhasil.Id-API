package com.raion.model.response

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @field:SerializedName("avatar")
    val avatar : String,

    @field:SerializedName("username")
    val username : String,

    @field:SerializedName("comment")
    val comment : String
)
