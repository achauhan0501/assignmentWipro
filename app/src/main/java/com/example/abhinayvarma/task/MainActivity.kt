package com.example.abhinayvarma.task

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.abhinayvarma.task.adapter.ItemsAdapter
import com.example.abhinayvarma.task.model.RowData
import com.example.abhinayvarma.task.presenter.GetDataContract
import com.example.abhinayvarma.task.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GetDataContract.View {

    var presenter: Presenter? = null

    override fun onGetDataSuccess(message: String?, data: ArrayList<RowData>) {
        list = data
        initialise()
        swipe_layout.isRefreshing = true
    }

    override fun onGetDataFailure(message: String?) {
        Toast.makeText(this@MainActivity, getString(R.string.no_internet_results), Toast.LENGTH_LONG).show()
    }

    var layoutManager: LinearLayoutManager? = null
    var list: ArrayList<RowData> = ArrayList()
    var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = Presenter(this)
        layoutManager = LinearLayoutManager(this)
        list = ArrayList()



        swipe_layout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            initialise()
            refresh()
            swipe_layout.isRefreshing = false
        })

    }

    fun refresh() {
        list.clear()
        initialise()

    }



    fun initialise() {
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
