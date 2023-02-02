package com.example

import com.example.db.DatabaseFactory
import com.example.db.UserTable
import com.example.repository.UserRepository
import com.example.repository.UserRepositoryImpl
import com.example.routes.authRoutes
import com.example.secuity.configureSecurity
import com.example.service.UserService
import com.example.service.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.init()
    transaction {
        SchemaUtils.create(UserTable)
    }
    install(ContentNegotiation) {
        jackson()
    }
    configureSecurity()
    val service: UserService = UserServiceImpl()
    val repository: UserRepository = UserRepositoryImpl(service)
    authRoutes(repository)

}
