package hr.foi.air.database.entities

data class Car(
    val id : String,
    val carName: String = "",
)
{
    constructor() : this("", "")
}
