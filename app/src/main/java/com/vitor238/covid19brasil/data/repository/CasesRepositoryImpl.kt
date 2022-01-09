package com.vitor238.covid19brasil.data.repository

import android.util.Log
import com.vitor238.covid19brasil.data.database.DatabaseBrazil
import com.vitor238.covid19brasil.data.database.DatabaseBrazilianState
import com.vitor238.covid19brasil.data.datasource.DataSource
import com.vitor238.covid19brasil.domain.repository.CasesRepository
import com.vitor238.covid19brasil.domain.toDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CasesRepositoryImpl(
    private val localDataSource: DataSource.Local,
    private val remoteDataSource: DataSource.Remote
) : CasesRepository {

    override suspend fun getCasesInBrazil(): DatabaseBrazil {
        return localDataSource.getTotalCasesInBrazil().getTotalCasesInBrazil()
    }

    override suspend fun getCasesByState(): List<DatabaseBrazilianState> {
        return localDataSource.getCasesByState().getCasesByState()
    }

    override suspend fun refreshCasesByState() {
        withContext(Dispatchers.IO) {
            try {
                val list = remoteDataSource.getBrazilianStates()
                localDataSource.getCasesByState().insertAll(*list.toDatabaseModel())
            } catch (e: Exception) {
                Log.i(TAG, "refreshCasesByState: ${e.message}")
            }
        }
    }

    override suspend fun refreshCasesInBrazil() {
        withContext(Dispatchers.IO) {
            try {
                val cases = remoteDataSource.getCasesInBrazil()
                localDataSource.getTotalCasesInBrazil().insert(cases.toDatabaseModel())
            } catch (e: Exception) {
                Log.i(TAG, "refreshCasesInBrazil(): ${e.message}")
            }
        }
    }

    companion object{
        private val TAG = CasesRepository::class.simpleName
    }

}