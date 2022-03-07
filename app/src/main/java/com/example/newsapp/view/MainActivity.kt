package com.example.newsapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.HeadlineAdapter
import com.example.newsapp.contract.ContractHeadlineNews
import com.example.newsapp.model.Articles
import com.example.newsapp.model.Constant
import com.example.newsapp.presenter.HeadlinePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*

class MainActivity() : AppCompatActivity(),
    ContractHeadlineNews.View {

    private var DETAIL_NEWS: String = "DETAIL_NEWS"

    private lateinit var headlineAdapter: HeadlineAdapter
    lateinit var presenterTopHeadline: HeadlinePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenterTopHeadline = HeadlinePresenter(this)
    }

    override fun onStart() {
        super.onStart()
        runBlocking {
            withContext(Dispatchers.IO){
                getData()
            }
        }
    }

    override suspend fun getData() {
        presenterTopHeadline.getHeadlineNews()
    }

    override fun initActivity() {

    }

    override fun initListener() {
        headlineAdapter = HeadlineAdapter(this, arrayListOf(),
            object : HeadlineAdapter.OnAdapterListener {
            override fun onClick(detailArticles: String) {
                val intent = Intent(this@MainActivity, WebViewActivity::class.java)
                intent.putExtra(DETAIL_NEWS, detailArticles)
                startActivity(intent)
                Log.d("TAG", detailArticles)
                //startActivity(Intent(applicationContext, WebViewActivity::class.java))
            }
        })

        rc_headline.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = headlineAdapter
        }
    }

    override fun onLoadingNews(loading: Boolean) {
        when (loading) {
            true -> {
                progress_circular.visibility = View.VISIBLE
            }
            false -> {
                progress_circular.visibility = View.GONE
            }
        }
    }

    override fun onResultNews(articles: List<Articles>) {
        headlineAdapter.setData(articles)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}