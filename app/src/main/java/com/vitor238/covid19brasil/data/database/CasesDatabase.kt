package com.vitor238.covid19brasil.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DatabaseBrazil::class,DatabaseBrazilianState::class], version = 1)
abstract class CasesDatabase : RoomDatabase() {
    abstract val casesByStateDao: CasesByStateDao
    abstract val totalCasesInBrazilDao:TotalCasesInBrazilDao
}