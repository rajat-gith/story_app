package com.example.secuity

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*


fun Application.configureSecurity() {
    JwtConfig.initialize("my-story-app")
    install(Authentication) {
        jwt {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.CLAIM).asInt()
                if (claim != null) {
                    UserIdPrinicipalForUser(claim)
                } else {
                    null
                }
            }
        }
    }
}