package com.example.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.model.Articles
import com.example.newsapp.model.Constant

class HeadlineAdapter(private val context: Context,
                      var topHeadline: ArrayList<Articles>,
                      var listener: OnAdapterListener) :
    RecyclerView.Adapter<HeadlineAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.headline_news_row, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = topHeadline[position]

        Glide.with(context)
            .load(model.urlToImage)
            .placeholder(R.drawable.no_img_available)
            .fitCenter()
            .into(holder.imageNews)
        holder.titleNews.text = model.title
        holder.contentNews.text = model.content

        holder.cardNews.setOnClickListener {
            val url = model.url.also { Constant.URL = it }
            with(listener) {
                onClick(url)
            }
            //Toast.makeText(context, model.url, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return topHeadline.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardNews: CardView = itemView.findViewById(R.id.card_news_row)
        val imageNews: ImageView = itemView.findViewById(R.id.img_headline_news_row)
        val titleNews: TextView = itemView.findViewById(R.id.txt_title_news_row)
        val contentNews: TextView = itemView.findViewById(R.id.txt_content_new_row)
    }

    fun setData(newData: List<Articles>) {
        topHeadline.clear()
        topHeadline.addAll(newData)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(detailArticles: String)
    }
}