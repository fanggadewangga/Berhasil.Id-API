package com.raion.data.table

import org.jetbrains.exposed.sql.Table

object ReviewTable : Table() {
    override val tableName: String = "review"

    val uid = reference("uid",UserTable.uid)
    val umkmId = reference("umkm_id", UMKMTable.umkmId)
    val buyerReview = varchar("buyer_review",512)
}