package hu.bme.httpmoneyconverter.retrofit

import hu.bme.httpmoneyconverter.data.MoneyResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//link: http://data.fixer.io/api/latest?access_key=969c37b5a73f8cb2d12c081dcbdc35e6
//host: https://data.fixer.io
// Path: /api/latest
// Query prams:
//   access_key=969c37b5a73f8cb2d12c081dcbdc35e6

interface MoneyAPI {

    @GET("/api/latest")
    fun getResult(@Query("access_key") key : String) : Call<MoneyResult>

}