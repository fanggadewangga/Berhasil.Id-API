package com.raion.data

import com.raion.data.table.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.net.URI

class DatabaseFactory {

    init {
        Database.connect(dataSource())
        transaction {
            val tables = listOf(
                UserTable, UMKMTable, ReviewTable, PostTable, CommentTable
            )
            tables.forEach {
                SchemaUtils.create(it)
            }
        }
    }

    private fun dataSource() : HikariDataSource{
        val config = HikariConfig()
        config.apply {
            driverClassName = System.getenv("JDBC_DRIVER")
            maximumPoolSize = 6
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"

            val uri = URI(System.getenv("DATABASE_URL"))
            val username = uri.userInfo.split(":").toTypedArray()[0]
            val password = uri.userInfo.split(":").toTypedArray()[1]
            jdbcUrl =
                "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?sslmode=require" + "&user=$username&password=$password"

            config.validate()
        }
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }
}