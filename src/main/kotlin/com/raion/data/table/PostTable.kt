package com.raion.data.table

import org.jetbrains.exposed.sql.Table

object PostTable : Table() {
    override val tableName: String = "post"

    val postId = varchar("post_id",128)
    val umkmId = reference("umkm_id", UMKMTable.umkmId)
    val image = varchar("image",512)
    val title = varchar("title", 128)
    val caption = varchar("caption",512)

    override val primaryKey: PrimaryKey? = PrimaryKey(postId)
}