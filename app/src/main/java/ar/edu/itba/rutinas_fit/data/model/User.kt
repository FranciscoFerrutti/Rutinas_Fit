package ar.edu.itba.rutinas_fit.data.model

import java.util.Date

data class User(
    var id: Int?,
    var username: String,
    var firstName: String,
    var lastName: String,
    var gender: String,
    var email: String,
    var phone: String?,
    var avatarUrl: String? = null,
    var lastActivity: Date? = null,
    var routines: List<Routine>? = null
)
