package com.example.abhinayvarma.task

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.abhinayvarma.task.adapter.ItemsAdapter
import com.example.abhinayvarma.task.model.ResponseData
import com.example.abhinayvarma.task.model.RowData
import com.example.abhinayvarma.task.network.RestClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var layoutManager: LinearLayoutManager ?= null
    var list : ArrayList<RowData> = ArrayList()
    var title : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutManager = LinearLayoutManager(this)
        list = ArrayList()
        callApi()

    }


    fun callApi(){
        RestClient.getClient().getData().enqueue(object : Callback<ResponseData> {
            override fun onResponse(p0: Call<ResponseData>?, response: Response<ResponseData>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null){
                        list = responseBody.rows
                        title = responseBody.title
                        initialise()
                    }else{
                        Toast.makeText(this@MainActivity, getString(R.string.no_data), Toast.LENGTH_LONG).show()

                    }

                }
            }

            override fun onFailure(p0: Call<ResponseData>?, p1: Throwable?) {
                Toast.makeText(this@MainActivity, getString(R.string.no_internet_results), Toast.LENGTH_LONG).show()

            }
        })
    }

    fun initialise(){
        rv.adapter = ItemsAdapter(this, list)
        rv.layoutManager = layoutManager
        val tv = TextView(applicationContext)
        // Create a LayoutParams for TextView
        val lp = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, // Width of TextView
                ViewGroup.LayoutParams.WRAP_CONTENT) // Height of TextView
        // Apply the layout parameters to TextView widget
        tv.layoutParams = lp
        tv.gravity = Gravity.CENTER_HORIZONTAL
        tv.textSize = 20f
        tv.text = title
        top_view.addView(tv)
    }



}
