package com.dipesh.jewellery.repo

import com.dipesh.jewellery.login.model.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        return dataSource.login(username, password)
    }
}