package com.dipesh.jewellery.repo

import com.dipesh.jewellery.login.model.LoggedInUser
import com.dipesh.jewellery.login.model.UserType
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        // TODO: handle loggedInUser authentication
        if (username.equals("regular")) {
            return Result.Success(LoggedInUser(UserType.REGULAR, "Regular"))
        } else if (username.equals("privilege")) {
            return Result.Success(LoggedInUser(UserType.PRIVILEGED, "Privileged"))
        } else {
            return Result.Error(IOException("Invalid credentials"))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}