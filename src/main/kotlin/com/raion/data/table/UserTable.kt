package com.raion.data.table

import org.jetbrains.exposed.sql.Table

object UserTable : Table() {
    override val tableName : String = "user"

    val uid = varchar("uid", 128)
    val username = varchar("username", 64)
    val avatar = varchar("avatar",128)

    override val primaryKey: PrimaryKey = PrimaryKey(uid)
}