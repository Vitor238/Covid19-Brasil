package com.vitor238.covid19brasil.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vitor238.covid19brasil.data.database.getDatabase
import com.vitor238.covid19brasil.data.repository.CasesRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = CasesRepository(database)
        return try {
            repository.refreshCasesByState()
            repository.refreshCasesInBrazil()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}