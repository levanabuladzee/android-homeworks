package abuladze.levan.homework6.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "car_user_id") val carUserId: Long,
    @ColumnInfo(name = "brand") val brand: String?,
    @ColumnInfo(name = "model") val model: String?
)