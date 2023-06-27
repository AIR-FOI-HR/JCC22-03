package hr.foi.air.database.entities

data class User(
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val surname: String = "",
)
