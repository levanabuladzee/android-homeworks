package abuladze.levan.homework3.model

data class Movie (
    val id : Long?,
    val name : String?,
    val genres : List<String>?,
    val officialSite : String?,
    val rating : Rating,
    val image : Image,
    val summary : String?
)

data class Rating(
    val average : Double?
)

data class Image (
    val medium : String?,
    val original : String?
)