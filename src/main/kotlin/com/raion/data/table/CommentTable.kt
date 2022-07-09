package com.raion.data.table

import org.jetbrains.exposed.sql.Table

object CommentTable : Table() {
    override val tableName: String = "comment"

    val uid = reference("uid", UserTable.uid)
    val postId = reference("post_id",PostTable.postId)
    val comment = varchar("comment", 512)
}