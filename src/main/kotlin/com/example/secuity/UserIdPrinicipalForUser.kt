package com.example.secuity

import java.security.Principal

data class UserIdPrinicipalForUser(val id : Int) : io.ktor.server.auth.Principal
