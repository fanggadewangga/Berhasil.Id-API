package com.raion.model.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @field:SerializedName("avatar")
    val avatar : String,

    @field:SerializedName("username")
    val username : String,

    @field:SerializedName("buyer_review")
    val buyerReview : String
)