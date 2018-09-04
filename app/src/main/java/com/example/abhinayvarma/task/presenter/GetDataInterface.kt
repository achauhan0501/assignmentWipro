package com.example.abhinayvarma.task.presenter

import android.content.Context
import com.example.abhinayvarma.task.model.RowData
import java.util.ArrayList

interface GetDataInterface {

    // View interface.
    interface View {
        fun onGetDataSuccess(message: String, list: ArrayList<RowData>, title: String)
        fun onGetDataFailure(message: String)
    }

    interface Presenter {
        fun getDataFromURL(context: Context)
    }

    interface Interactor {
        fun initRetrofitCall(context: Context)

    }

    interface onGetDataListener {
        fun onSuccess(message: String, list: ArrayList<RowData>, title: String)
        fun onFailure(message: String)
    }
}