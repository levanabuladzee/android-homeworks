package abuladze.levan.homework6.database

import androidx.room.*

@Dao
interface CarDao {
    @Query("SELECT * FROM car")
    fun getAll(): List<Car>

    @Query("SELECT * FROM car WHERE uid IN (:carIds)")
    fun loadAllByIds(carIds: IntArray): List<Car>

    @Query("SELECT * FROM car WHERE brand LIKE :brand AND model LIKE :model LIMIT 1")
    fun findByName(brand: String, model: String): Car

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg cars: Car)

    @Delete
    fun delete(car: Car)

    @Query("DELETE FROM car")
    fun deleteAll()
}