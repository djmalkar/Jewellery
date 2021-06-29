package com.dipesh.jewellery.login

import com.dipesh.jewellery.login.model.UserType


/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
        val displayName: String,
        val userType : UserType
        //... other data fields that may be accessible to the UI
)