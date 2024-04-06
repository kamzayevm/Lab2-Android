package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab2.adapter.FigureAdapter
import com.example.lab2.databinding.ActivityMainBinding
import com.example.lab2.model.Figure
import com.example.lab2.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.recyclerViewFigure
        recyclerView.layoutManager = LinearLayoutManager(this)

        val api = ApiClient.instance
        binding.searchButton.setOnClickListener {
            val searchQuery = binding.searchText.text.toString()
            val call = api.getFigures(searchQuery)
            call.enqueue(object : Callback<List<Figure>> {
                override fun onResponse(call: Call<List<Figure>>, response: Response<List<Figure>>) {
                    if (response.isSuccessful) {
                        val figures = response.body()
                        val adapter = figures?.let { it1 -> FigureAdapter(it1) }
                        recyclerView.adapter = adapter
                    } else {
                        println("HTTP Failed to get historical figures: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<Figure>>, t: Throwable) {
                    println("HTTP Failed to get historical figures: ${t.message}")
                }
            })
        }
    }
}
