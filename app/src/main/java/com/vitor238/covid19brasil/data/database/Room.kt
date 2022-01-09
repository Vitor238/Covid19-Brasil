package com.vitor238.covid19brasil.data.database
import androidx.room.*

@Dao
interface CasesByStateDao {
    @Query("select * from cases_by_state_table")
    suspend fun getCasesByState(): List<DatabaseBrazilianState>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg casesByState: DatabaseBrazilianState)
}

@Dao
interface TotalCasesInBrazilDao {
    @Query("select * from total_cases_brazil_table")
    suspend fun getTotalCasesInBrazil(): DatabaseBrazil

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(databaseBrazil: DatabaseBrazil)
}

@Database(entities = [DatabaseBrazil::class,DatabaseBrazilianState::class], version = 1)
abstract class CasesDatabase : RoomDatabase() {
    abstract val casesByStateDao: CasesByStateDao
    abstract val totalCasesInBrazilDao:TotalCasesInBrazilDao
}