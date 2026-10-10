package com.blidaoui.postanalyzer.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnalysisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AnalysisEntity)

    @Delete
    suspend fun delete(entity: AnalysisEntity)

    @Query("SELECT * FROM analysis_results ORDER BY timestamp DESC")
    fun getAll(): Flow<List<AnalysisEntity>>
}
