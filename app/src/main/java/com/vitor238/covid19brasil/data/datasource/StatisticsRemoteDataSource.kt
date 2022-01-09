package com.vitor238.covid19brasil.data.datasource

import com.vitor238.covid19brasil.data.api.BrazilService
import com.vitor238.covid19brasil.data.api.BrazilianStatesService
import com.vitor238.covid19brasil.data.model.BrazilianStateData
import com.vitor238.covid19brasil.data.model.CountryData

class StatisticsRemoteDataSource(
    private val brazilianStatesService: BrazilianStatesService,
    private val brazilService: BrazilService
) : DataSource.Remote {

    override suspend fun getBrazilianStates(): List<BrazilianStateData> {
        return brazilianStatesService.casesByState().data
    }

    override suspend fun getCasesInBrazil(): CountryData {
        return brazilService.totalCasesBrazil().countryData
    }
}