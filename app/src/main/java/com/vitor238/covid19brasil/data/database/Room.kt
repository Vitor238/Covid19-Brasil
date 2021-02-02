package com.vitor238.covid19brasil.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CasesByStateDao {
    @Query("select * from cases_by_state_table")
    fun getCasesByState(): LiveData<List<DatabaseBrazilianState>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg casesByState: DatabaseBrazilianState)
}

@Dao
interface TotalCasesInBrazilDao {
    @Query("select * from total_cases_brazil_table")
    fun getTotalCasesInBrazil(): LiveData<DatabaseBrazil>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseBrazil: DatabaseBrazil)
}

@Database(entities = [DatabaseBrazil::class,DatabaseBrazilianState::class], version = 1)
abstract class CasesDatabase : RoomDatabase() {
    abstract val casesByStateDao: CasesByStateDao
    abstract val totalCasesInBrazilDao:TotalCasesInBrazilDao
}

private lateinit var INSTANCE: CasesDatabase

fun getDatabase(context: Context): CasesDatabase {
    synchronized(CasesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                CasesDatabase::class.java,
                "cases").build()
        }
    }
    return INSTANCE
}