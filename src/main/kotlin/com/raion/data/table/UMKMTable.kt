package com.raion.data.table

import org.jetbrains.exposed.sql.Table

object UMKMTable : Table() {
    override val tableName: String = "umkm"

    val umkmId = varchar("umkm_id",128)
    val umkmName = varchar("umkm_name",128)
    val description = varchar("description",1024)
    val address = varchar("address",256)
    val category = varchar("category",64)
    val sellTime = integer("sell_time")
    val image = varchar("image", 512)
    val itemSold = integer("item_sold")

    override val primaryKey: PrimaryKey? = PrimaryKey(umkmId)
}