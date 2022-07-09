package com.raion.model.body

import com.google.gson.annotations.SerializedName

data class ReviewBody(
    @field:SerializedName("uid")
    val uid : String,

    @field:SerializedName("buyer_review")
    val buyerReview : String
)
