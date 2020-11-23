package abuladze.levan.homework6.database

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithCars(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "uid",
        entityColumn = "car_user_id"
    )
    val cars: List<Car>
)