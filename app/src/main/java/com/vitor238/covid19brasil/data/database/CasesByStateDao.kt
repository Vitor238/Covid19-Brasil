package com.vitor238.covid19brasil.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CasesByStateDao {
    @Query("select * from cases_by_state_table")
    fun getCasesByState(): Flow<List<DatabaseBrazilianState>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg casesByState: DatabaseBrazilianState)
}