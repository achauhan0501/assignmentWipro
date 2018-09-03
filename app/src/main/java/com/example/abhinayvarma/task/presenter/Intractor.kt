package com.example.abhinayvarma.task.presenter

import android.content.Context
import android.widget.Toast
import com.example.abhinayvarma.task.R
import com.example.abhinayvarma.task.model.ResponseData
import com.example.abhinayvarma.task.network.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Intractor(var mOnGetDatalistener: GetDataContract.onGetDataListener) : GetDataContract.Interactor {

    //    internal var allcountry = ArrayList<RowData>()
//    internal var allCountriesData = ArrayList<String>()


    override fun initRetrofitCall(context: Context?, url: String?) {
        RestClient.getClient().getData().enqueue(object : Callback<ResponseData> {
            override fun onResponse(p0: Call<ResponseData>?, response: Response<ResponseData>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        /*list = responseBody.rows
                        title = responseBody.title
                        initialise()
                        swipe_layout.isRefreshing = true*/
                    } else {
                        Toast.makeText(context, context?.getString(R.string.no_data), Toast.LENGTH_LONG).show()

                    }

                }
            }

            override fun onFailure(p0: Call<ResponseData>?, p1: Throwable?) {
                Toast.makeText(context, context?.getString(R.string.no_internet_results), Toast.LENGTH_LONG).show()

            }
        })
    }
}