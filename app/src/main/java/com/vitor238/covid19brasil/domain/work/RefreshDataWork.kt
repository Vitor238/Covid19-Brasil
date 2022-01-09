package com.vitor238.covid19brasil.domain.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vitor238.covid19brasil.domain.repository.CasesRepository
import retrofit2.HttpException

class RefreshDataWorker(
    appContext: Context,
    private val repository: CasesRepository, params: WorkerParameters
) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            repository.refreshCasesByState()
            repository.refreshCasesInBrazil()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}