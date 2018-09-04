package com.example.abhinayvarma.task

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.abhinayvarma.task.adapter.ItemsAdapter
import com.example.abhinayvarma.task.model.RowData
import com.example.abhinayvarma.task.presenter.GetDataInterface
import com.example.abhinayvarma.task.presenter.PresenterLogic
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GetDataInterface.View {

    var presenter: PresenterLogic? = null
    var layoutManager: LinearLayoutManager? = null
    var list: ArrayList<RowData> = ArrayList()
    var title: String = ""
    var snackbar: Snackbar? = null

    override fun onGetDataSuccess(message: String, data: java.util.ArrayList<RowData>, heading: String) {
        list = data
        title = heading
        initialise()
        swipe_layout.isRefreshing = false
    }

    override fun onGetDataFailure(message: String) {
        Toast.makeText(this@MainActivity, getString(R.string.no_internet_results), Toast.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // save your state here
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = PresenterLogic(this@MainActivity)
        //Internet check
        if (isNetworkAvailable(this))
            presenter?.getDataFromURL(this@MainActivity)
        else
            showNoInternetSnackBar()

        layoutManager = LinearLayoutManager(this)
        list = ArrayList()



        swipe_layout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            refresh()
            swipe_layout.isRefreshing = false
        })

    }

    fun refresh() {
        list.clear()
        initialise()
        presenter?.getDataFromURL(this@MainActivity)
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

    /**
     * Show No Internet Snackbar
     */
    fun showNoInternetSnackBar() {
        snackbar = Snackbar.make(ll_parent, getString(R.string.no_internet_results), Snackbar.LENGTH_INDEFINITE)
        snackbar?.setAction("RETRY", View.OnClickListener {
            if (isNetworkAvailable(this)) {
                snackbar?.dismiss()
                presenter?.getDataFromURL(this@MainActivity)

            } else {
                showNoInternetSnackBar()
            }
        })
        snackbar?.duration = Snackbar.LENGTH_INDEFINITE
        snackbar?.setActionTextColor(Color.WHITE)
        val sbView = snackbar?.view
        val textView = sbView?.findViewById(android.support.design.R.id.snackbar_text) as TextView
        snackbar?.show()
    }
    /*
        *  Internet check
        * */
    fun isNetworkAvailable(context: Context): Boolean {
        val conmanager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = conmanager.activeNetworkInfo
        if (info != null && info.isAvailable) {
            return info.isConnected
        }
        return false
    }



}
