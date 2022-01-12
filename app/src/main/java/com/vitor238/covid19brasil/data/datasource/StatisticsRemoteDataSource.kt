package com.vitor238.covid19brasil.data.datasource

import com.vitor238.covid19brasil.data.api.BrazilService
import com.vitor238.covid19brasil.data.api.BrazilianStatesService
import com.vitor238.covid19brasil.data.model.BrazilianStateData
import com.vitor238.covid19brasil.data.model.CountryData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StatisticsRemoteDataSource(
    private val brazilianStatesService: BrazilianStatesService,
    private val brazilService: BrazilService
) : DataSource.Remote {

    override suspend fun getBrazilianStates(): Flow<List<BrazilianStateData>> = flow {
        emit(brazilianStatesService.casesByState().data)
    }

    override suspend fun getCasesInBrazil(): Flow<CountryData> = flow {
        emit(brazilService.totalCasesBrazil().countryData)
    }
}