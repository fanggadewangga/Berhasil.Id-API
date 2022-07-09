package com.raion.model.body

import com.google.gson.annotations.SerializedName

data class UserBody(
    @field:SerializedName("username")
    val username : String,

    @field:SerializedName("avatar")
    val avatar : String
)
