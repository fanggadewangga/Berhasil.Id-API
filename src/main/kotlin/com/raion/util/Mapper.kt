package com.raion.util

import com.raion.data.table.*
import com.raion.model.response.*
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToUMKMResponse(
    reviews: List<ReviewResponse>,
    posts: List<PostLiteResponse>
) = UMKMResponse (
    umkmId = this[UMKMTable.umkmId],
    umkmName = this[UMKMTable.umkmName],
    description = this[UMKMTable.description],
    address = this[UMKMTable.address],
    category = this[UMKMTable.category],
    sellTime = this[UMKMTable.sellTime],
    image = this[UMKMTable.image],
    itemSold = this[UMKMTable.itemSold],
    posts = posts,
    reviews = reviews
)

fun ResultRow.mapRowToReviewResponse() =
    ReviewResponse(
        username = this[UserTable.username],
        avatar = this[UserTable.avatar],
        buyerReview = this[ReviewTable.buyerReview]
    )

fun ResultRow.mapRowToUMKMLiteResponse() =
    UMKMLiteResponse(
        umkmId = this[UMKMTable.umkmId],
        umkmName = this[UMKMTable.umkmName],
        category = this[UMKMTable.category],
        image = this[UMKMTable.image]
    )

fun ResultRow.mapRowToPostResponse(
    comments : List<CommentResponse>
) = PostResponse(
    postId = this[PostTable.postId],
    title = this[PostTable.title],
    image = this[PostTable.image],
    caption = this[PostTable.caption],
    comments = comments
)

fun ResultRow.mapRowToPostLiteResponse() = PostLiteResponse(
    postId = this[PostTable.postId],
    title = this[PostTable.title],
    image = this[PostTable.image],
    caption = this[PostTable.caption]
)

fun ResultRow.mapRowToCommentResponse() =
    CommentResponse(
        username = this[UserTable.username],
        avatar = this[UserTable.avatar],
        comment = this[CommentTable.comment]
    )