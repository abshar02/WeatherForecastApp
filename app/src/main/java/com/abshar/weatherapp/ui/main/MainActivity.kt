package com.abshar.weatherapp.ui.main

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.View
import android.widget.*
import com.abshar.weatherapp.R
import com.abshar.weatherapp.ui.base.BaseActivity
import com.abshar.weatherapp.ui.forecastAdapter.ForecastAdapter
import com.abshar.weatherapp.ui.forecastItem.ForecastItemViewModel


class MainActivity : BaseActivity<MainPresenter>(), MainView {
    private lateinit var spinner: ProgressBar
    private lateinit var error: TextView
    private lateinit var forecastList: RecyclerView
    private lateinit var forecast: LinearLayout
    private lateinit var location: TextView
    private lateinit var todayDegree: TextView
    private lateinit var todayDescription: TextView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var useMyLocationBtn: Button


    private var errorMsgOf = mapOf(
            ErrorTypes.CALL_ERROR to R.string.error_common,
            ErrorTypes.NO_RESULT_FOUND to R.string.no_results
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.spinner = findViewById(R.id.loadingSpinner)
        this.error = findViewById(R.id.error)
        this.forecastList = findViewById(R.id.forecastList)
        this.forecast = findViewById(R.id.forecast)
        this.location = findViewById(R.id.location)
        this.todayDegree = findViewById(R.id.todayDegree)
        this.todayDescription = findViewById(R.id.todayDescription)
        this.refreshLayout = findViewById(R.id.mainScreen)
        this.refreshLayout.setOnRefreshListener {
            presenter.onRefresh()
        }
        this.useMyLocationBtn = findViewById(R.id.useMyLocationButton)
        this.useMyLocationBtn.setOnClickListener {
            presenter.getLocation { l: Location? -> presenter.getForecastByLocation(l) }
        }

        initializeForecastList()
        presenter.onCreate()
    }


    private fun initializeForecastList() {
        forecastList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ForecastAdapter()
        }
    }

    override fun startLoading() {
        forecast.visibility = View.GONE
        error.visibility = View.GONE
        refreshLayout.isRefreshing = true
    }

    override fun finishLoading() {
        forecast.visibility = View.VISIBLE
        spinner.visibility = View.GONE
        refreshLayout.isRefreshing = false
    }

    override fun updateForecast(forecasts: List<ForecastItemViewModel>) {
        if (forecasts.isEmpty()) error.visibility = View.VISIBLE
        (forecastList.adapter as ForecastAdapter).addForecast(
                forecasts.slice(1 until forecasts.size)
        )
        todayDegree.text = "${forecasts[0].temperature.atDay.toInt()}°"
        todayDescription.text = forecasts[0].description
    }

    override fun showError(errorType: ErrorTypes) {
        errorMsgOf[errorType]?.let {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        val errorMsg: Int = errorMsgOf[errorType] ?: R.string.error_common
        error.setText(errorMsg)
        error.visibility = View.VISIBLE;
    }

    override fun requestPermission(permission: String, code: Int) {
        val permissionRequestDeclined = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        if (permissionRequestDeclined) {
            this.showPermissionLostDialog()
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(permission),
                    code
            )
        }
    }

    override fun showPermissionLostDialog() {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle(R.string.permission_missed_title)
                .setMessage(R.string.permission_missed_description)
                .setCancelable(false)
        val alert = builder.create()
        alert.show()
    }

    override fun onRequestPermissionsResult(code: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(code, permissions, grantResults)
        presenter.onRequestPermissionsResult(code, grantResults)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            queryHint = resources.getString(R.string.search_hint)
            maxWidth = Integer.MAX_VALUE
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    presenter.getForecast(query, 10) {
                        presenter.prefs.edit().putString("city", query).apply()
                    }
                    return false
                }

            })

        }

        return true
    }

    override fun setCity(city: String) {
        location.text = city
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }
}