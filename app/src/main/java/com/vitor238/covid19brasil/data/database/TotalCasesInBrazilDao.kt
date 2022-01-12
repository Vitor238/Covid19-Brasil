package com.vitor238.covid19brasil.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TotalCasesInBrazilDao {
    @Query("select * from total_cases_brazil_table")
    fun getTotalCasesInBrazil(): Flow<DatabaseBrazil>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(databaseBrazil: DatabaseBrazil)
}