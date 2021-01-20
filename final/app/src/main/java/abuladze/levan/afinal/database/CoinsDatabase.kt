package abuladze.levan.afinal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Coins::class], version = 1, exportSchema = false)
abstract class CoinsDatabase : RoomDatabase() {
    abstract fun coinsDao(): CoinsDao

    companion object {
        @Volatile
        private var INSTANCE: CoinsDatabase? = null

        fun getDatabase(context: Context): CoinsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinsDatabase::class.java,
                    "coins_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}