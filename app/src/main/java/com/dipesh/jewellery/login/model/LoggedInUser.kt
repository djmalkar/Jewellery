package com.dipesh.jewellery.login.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
        val userType: UserType,
        val displayName: String
)

enum class UserType {
        REGULAR,
        PRIVILEGED
}