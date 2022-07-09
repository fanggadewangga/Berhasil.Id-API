package com.raion.model.body

import com.google.gson.annotations.SerializedName

data class CommentBody(
    @field:SerializedName("uid")
    val uid : String,

    @field:SerializedName("comment")
    val comment : String
)
