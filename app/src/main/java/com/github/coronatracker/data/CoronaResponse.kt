package com.github.coronatracker.data


import com.google.gson.annotations.SerializedName

data class CoronaResponse(
    var brazil: List<BrazilResponse?>? = null,
    var world: List<WorldResponse?>? = null
) {
    data class BrazilResponse(
        var date: String? = null, // 08/03/2020
        var time: String? = null, // 17:30
        val values: MutableList<ValueResponse> = mutableListOf()
    ) {
        data class ValueResponse(
            var broadcast: Boolean? = null, // true
            var cases: Int? = null, // 1
            var comments: String? = null, // 1 Portador assintomático
            var refuses: Int? = null, // 24
            var suspects: Int? = null, // 24
            var uid: Int? = null // 53
        )
    }

    data class WorldResponse(
        var date: String? = null, // 08/03/2020
        var time: String? = null, // 14:10
        var total: TotalResponse? = null,
        val values: MutableList<ValueResponse> = mutableListOf()
    ) {
        data class TotalResponse(
            var cases: Int? = null, // 105586
            var casesNew: Int? = null, // 3656
            var deaths: Int? = null, // 3584
            var deathsNew: Int? = null // 98
        )

        data class ValueResponse(
            var broadcast: Boolean? = null, // true
            var cases: Int? = null, // 696
            var casesNew: Int? = null, // 1
            var comments: String? = null, // Casos identificados em um navio de cruzeiro atualmente em águas territoriais japonesas.
            var deaths: Int? = null, // 7
            var deathsNew: Int? = null, // 1
            var uid: String? = null // X1
        )
    }
}