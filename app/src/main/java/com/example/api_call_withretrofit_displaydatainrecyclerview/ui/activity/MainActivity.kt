package com.example.api_call_withretrofit_displaydatainrecyclerview.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_call_withretrofit_displaydatainrecyclerview.R
import com.example.api_call_withretrofit_displaydatainrecyclerview.databinding.ActivityMainBinding
import com.example.api_call_withretrofit_displaydatainrecyclerview.model.MyDataItem
import com.example.api_call_withretrofit_displaydatainrecyclerview.network.ApiInterface
import com.example.api_call_withretrofit_displaydatainrecyclerview.ui.adapters.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager:LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //initialize the recyclerView
        val recyclerView = binding.recyclerViewUsers
        //prevent the recyclerView from changing the size
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        //set the layoutManager to the recyclerView
        recyclerView.layoutManager = linearLayoutManager

        getMyData()

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        //create a variable to get data from retrofitBuilder
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                //to show the data in the recyclerView
                val responseBody = response.body()!!
                myAdapter = MyAdapter(baseContext,responseBody)
                //to notify the adapter if the data changes
                myAdapter.notifyDataSetChanged()
                //set the recyclerView to the adapter
                val recyclerView = binding.recyclerViewUsers
                recyclerView.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                d("Error!","onFailure: "+t.message)
            }
        })

    }
}