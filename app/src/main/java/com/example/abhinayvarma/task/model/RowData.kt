package com.example.abhinayvarma.task.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RowData {
    @SerializedName("title")
    @Expose
    var title: String = ""
    @SerializedName("description")
    @Expose
    var description: String = ""
    @SerializedName("imageHref")
    @Expose
    var imageHref: String = ""
}