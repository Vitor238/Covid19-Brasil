package com.vitor238.covid19brasil.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.vitor238.covid19brasil.data.database.CasesDatabase
import com.vitor238.covid19brasil.data.database.toDomainModel
import com.vitor238.covid19brasil.data.domain.Brazil
import com.vitor238.covid19brasil.data.domain.BrazilianState
import com.vitor238.covid19brasil.data.network.Network
import com.vitor238.covid19brasil.data.network.toDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CasesRepository(private val database: CasesDatabase) {

    val casesInBrazil: LiveData<Brazil> =
        Transformations.map(database.totalCasesInBrazilDao.getTotalCasesInBrazil()) {
            it?.toDomainModel()
        }


    val casesByState: LiveData<List<BrazilianState>> =
        Transformations.map(database.casesByStateDao.getCasesByState()) { list ->
            list.sortedByDescending { it.cases }.toDomainModel()
        }

    suspend fun refreshCasesByState() {
        withContext(Dispatchers.IO) {
            try{
                val list = Network.brazilianStates.casesByState()
                database.casesByStateDao.insertAll(*list.toDatabaseModel())
            }catch (e:Exception){
                Log.i(TAG, "refreshCasesByState: ${e.message}")
            }
        }
    }

    suspend fun refreshCasesInBrazil() {
        withContext(Dispatchers.IO) {
            try{
                val cases = Network.brazil.totalCasesBrazil()
                database.totalCasesInBrazilDao.insert(cases.data.toDatabaseModel())
            }catch (e:Exception){
                Log.i(TAG, "refreshCasesInBrazil(): ${e.message}")
            }
        }
    }

    companion object{
        private val TAG = CasesRepository::class.simpleName
    }

}