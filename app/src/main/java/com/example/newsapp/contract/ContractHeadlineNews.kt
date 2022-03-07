package com.example.newsapp.contract

import com.example.newsapp.model.Articles

interface ContractHeadlineNews {

    interface Presenter {
        suspend fun getHeadlineNews()
    }

    interface View {
        suspend fun getData()
        fun initActivity()
        fun initListener()
        fun onLoadingNews(loading: Boolean)
        fun onResultNews(articles: List<Articles>)
        fun showMessage(message: String)
    }
}