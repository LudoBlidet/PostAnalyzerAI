package com.blidaoui.postanalyzer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [AnalysisEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AnalysisDatabase : RoomDatabase() {
    abstract fun analysisDao(): AnalysisDao

    companion object {
        @Volatile
        private var INSTANCE: AnalysisDatabase? = null

        fun getDatabase(context: Context): AnalysisDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnalysisDatabase::class.java,
                    "post_analyzer_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
