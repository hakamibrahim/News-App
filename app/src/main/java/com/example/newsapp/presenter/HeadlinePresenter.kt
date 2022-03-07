package com.example.newsapp.presenter

import com.example.newsapp.api.ApiService
import com.example.newsapp.contract.ContractHeadlineNews
import com.example.newsapp.model.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeadlinePresenter(private val view: ContractHeadlineNews.View)
    : ContractHeadlineNews.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override suspend fun getHeadlineNews() {
        view.onLoadingNews(true)
        val service = ApiService.endpoint.getTopHeadline(Constant.COUNTRY, Constant.API_KEY)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.body()
            val articles = response!!.articles
            view.onLoadingNews(false)
            view.onResultNews(articles)
        }
    }
}