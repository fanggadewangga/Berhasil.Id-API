package com.raion.repository

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.raion.data.DatabaseFactory
import com.raion.data.table.*
import com.raion.model.body.*
import com.raion.util.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like

class BerhasilIdRepository {
    private var dbFactory : DatabaseFactory = DatabaseFactory()

    suspend fun addNewUser(body: UserBody){
        dbFactory.dbQuery {
            UserTable.insert { table ->
                table[uid] = "USER${NanoIdUtils.randomNanoId()}"
                table[username] = body.username
                table[avatar] = body.avatar
            }
        }
    }

    suspend fun updateUser(uid: String, body: UserBody){
        dbFactory.dbQuery {
            UserTable.update(
                where = {UserTable.uid.eq(uid)}
            ) {
                table ->
                table[username] = body.username
                table[avatar] = body.avatar
            }
        }
    }

    suspend fun addNewUMKM(body : UMKMBody){
        dbFactory.dbQuery {
            UMKMTable.insert {table ->
                table[umkmId] = "UMKM${NanoIdUtils.randomNanoId()}"
                table[umkmName] = body.umkmName
                table[description] = body.description
                table[address] = body.address
                table[category] = body.category
                table[sellTime] = body.sellTime
                table[image] = body.image
                table[itemSold] = body.itemSold
            }
        }
    }

    suspend fun deleteUMKM(umkmId : String){
        dbFactory.dbQuery {
            UMKMTable.deleteWhere { UMKMTable.umkmId.eq(umkmId) }
        }
    }

    suspend fun getAllUMKMs() = dbFactory.dbQuery {
        UMKMTable
            .selectAll()
            .groupBy(UMKMTable.umkmId)
            .mapNotNull { it.mapRowToUMKMLiteResponse() }
    }

    suspend fun addNewPosts(umkmId: String,body: PostBody){
        dbFactory.dbQuery {
            PostTable.insert { table ->
                table[postId] = "POST${NanoIdUtils.randomNanoId()}"
                table[this.umkmId] = umkmId
                table[title] = body.title
                table[image] = body.image
                table[caption] = body.caption
            }
        }
    }

    suspend fun addNewComment(postId : String, body: CommentBody){
        dbFactory.dbQuery {
            CommentTable.insert { table ->
                table[uid] = body.uid
                table[this.postId] = postId
                table[comment] = body.comment
            }
        }
    }

    suspend fun getAllPosts() = dbFactory.dbQuery {
        PostTable
            .selectAll()
            .groupBy(PostTable.postId)
            .mapNotNull { it.mapRowToPostLiteResponse()}
    }

    suspend fun getPostDetail(postId: String){
        dbFactory.dbQuery {
            val comment = CommentTable.join(UserTable,JoinType.FULL)
                .select{
                    CommentTable.postId.eq(postId)
                }.mapNotNull {
                    it.mapRowToCommentResponse()
                }
        }

        PostTable.join(CommentTable, JoinType.LEFT){
            PostTable.postId.eq(CommentTable.postId)
        }.slice(
            PostTable.postId,
            PostTable.umkmId,
            PostTable.image,
            PostTable.title,
            PostTable.caption
        )
    }

    suspend fun getUMKMDetail(umkmId: String){
        val posts = getAllPosts()

        dbFactory.dbQuery {
            val reviews = ReviewTable.join(UserTable, joinType = JoinType.FULL)
                .select{
                    ReviewTable.umkmId.eq(umkmId)
                }.mapNotNull {
                    it.mapRowToReviewResponse()
                }

            UMKMTable.join(ReviewTable, JoinType.LEFT){
                UMKMTable.umkmId.eq(ReviewTable.umkmId)
            }.slice(
                UMKMTable.umkmId,
                UMKMTable.umkmName,
                UMKMTable.description,
                UMKMTable.address,
                UMKMTable.category,
                UMKMTable.sellTime,
                UMKMTable.image,
                UMKMTable.itemSold,
                ReviewTable.buyerReview
            ).select{
                UMKMTable.umkmId.eq(umkmId)
            }
                .groupBy(UMKMTable.umkmId).firstNotNullOf {
                    return@dbQuery it.mapRowToUMKMResponse(reviews,posts)
                }
        }
    }

    suspend fun addNewReview(umkmId: String, body: ReviewBody){
        dbFactory.dbQuery {
            ReviewTable.insert { table ->
                table[uid] = body.uid
                table[this.umkmId] = umkmId
                table[buyerReview] = body.buyerReview
            }
        }
    }

    suspend fun searchMenu(query : String) = dbFactory.dbQuery {
        UMKMTable.join(ReviewTable,JoinType.LEFT) {
            UMKMTable.umkmId.eq(ReviewTable.umkmId)
        }.slice(
            UMKMTable.umkmId,
            UMKMTable.umkmName,
            UMKMTable.description,
            UMKMTable.address,
            UMKMTable.category,
            UMKMTable.sellTime,
            UMKMTable.image,
            UMKMTable.itemSold,
            ReviewTable.buyerReview
        ).select(
            LowerCase(UMKMTable.umkmName).like("%$query%".lowercase())
        ).groupBy(UMKMTable.umkmId)
            .mapNotNull {
                it.mapRowToUMKMLiteResponse()
            }

    }
}