package com.example.abhinayvarma.task.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseData {
    @SerializedName("title")
    @Expose
    var title: String = ""
    @SerializedName("rows")
    @Expose
    var rows: ArrayList<RowData> = ArrayList()
}