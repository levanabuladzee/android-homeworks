package abuladze.levan.afinal.database

import androidx.room.*

@Dao
interface CoinsDao {
    @Query("select * from coins")
    fun all(): List<Coins>

    @Query("select * from coins where symbol = :symbol")
    fun findBySymbol(symbol: String): Coins?

    @Query("select * from coins where isFavourite = 1")
    fun favourites(): List<Coins>

    @Query("select symbol from coins where isFavourite = 1")
    suspend fun favouriteSymbols(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<Coins>)

    @Update
    fun update(data: Coins): Int

    @Query("delete from coins")
    fun deleteAll()
}