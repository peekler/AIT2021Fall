package hu.bme.httpmoneyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.httpmoneyconverter.data.MoneyResult
import hu.bme.httpmoneyconverter.databinding.ActivityMainBinding
import hu.bme.httpmoneyconverter.retrofit.MoneyAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://data.fixer.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val moneyService = retrofit.create(MoneyAPI::class.java)

        binding.btnGet.setOnClickListener {

            val moneyCall = moneyService.getResult("969c37b5a73f8cb2d12c081dcbdc35e6")

            moneyCall.enqueue(object : Callback<MoneyResult> {
                override fun onFailure(call: Call<MoneyResult>, t: Throwable) {
                    binding.tvData.text = t.message
                }

                override fun onResponse(call: Call<MoneyResult>, response: Response<MoneyResult>) {
                    var moneyResult = response.body()

                    binding.tvData.text = "HUF: ${moneyResult?.rates?.HUF}"
                }
            })
        }

    }
}