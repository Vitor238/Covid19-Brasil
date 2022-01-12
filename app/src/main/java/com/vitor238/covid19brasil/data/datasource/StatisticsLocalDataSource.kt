package com.vitor238.covid19brasil.data.datasource

import com.vitor238.covid19brasil.data.database.CasesByStateDao
import com.vitor238.covid19brasil.data.database.CasesDatabase
import com.vitor238.covid19brasil.data.database.TotalCasesInBrazilDao

class StatisticsLocalDataSource(private val roomDatabase: CasesDatabase) : DataSource.Local {

    override fun getCasesInBrazilDao(): TotalCasesInBrazilDao {
        return roomDatabase.totalCasesInBrazilDao
    }

    override fun getCasesByStateDao(): CasesByStateDao {
        return roomDatabase.casesByStateDao
    }
}