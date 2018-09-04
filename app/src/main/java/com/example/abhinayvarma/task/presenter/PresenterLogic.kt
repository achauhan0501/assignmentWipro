package com.example.abhinayvarma.task.presenter

import android.content.Context
import com.example.abhinayvarma.task.model.RowData
import java.util.ArrayList

class PresenterLogic(var mGetDataView: GetDataInterface.View) :
        GetDataInterface.Presenter,
        GetDataInterface.onGetDataListener {


    private val mIntractor: Intractor = Intractor(this@PresenterLogic)


    override fun getDataFromURL(context: Context) {
        mIntractor.initRetrofitCall(context)
    }

    override fun onSuccess(message: String, list: ArrayList<RowData>, title: String) {
        mGetDataView.onGetDataSuccess(message, list, title)
    }

    override fun onFailure(message: String) {
        mGetDataView.onGetDataFailure(message)
    }
}