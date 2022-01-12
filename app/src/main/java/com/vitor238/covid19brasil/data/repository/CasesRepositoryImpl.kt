package com.vitor238.covid19brasil.data.repository

import android.util.Log
import com.vitor238.covid19brasil.data.database.DatabaseBrazil
import com.vitor238.covid19brasil.data.database.DatabaseBrazilianState
import com.vitor238.covid19brasil.data.datasource.DataSource
import com.vitor238.covid19brasil.domain.repository.CasesRepository
import com.vitor238.covid19brasil.domain.toDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class CasesRepositoryImpl(
    private val localDataSource: DataSource.Local,
    private val remoteDataSource: DataSource.Remote
) : CasesRepository {

    override suspend fun getCasesInBrazil(): Flow<DatabaseBrazil> {
        return localDataSource.getCasesInBrazilDao().getTotalCasesInBrazil()
    }

    override suspend fun getCasesByState(): Flow<List<DatabaseBrazilianState>> {
        return localDataSource.getCasesByStateDao().getCasesByState()
    }

    override suspend fun refreshCasesByState() {
        remoteDataSource.getBrazilianStates()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                Log.i(TAG, "refreshCasesByState: ${e.message}")
            }
            .collect { list ->
                localDataSource.getCasesByStateDao().insertAll(*list.toDatabaseModel())
            }
    }

    override suspend fun refreshCasesInBrazil() {
        remoteDataSource.getCasesInBrazil()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                Log.i(TAG, "refreshCasesInBrazil(): ${e.message}")
            }
            .collect { cases ->
                localDataSource.getCasesInBrazilDao().insert(cases.toDatabaseModel())
            }
    }

    companion object {
        private val TAG = CasesRepository::class.simpleName
    }

}