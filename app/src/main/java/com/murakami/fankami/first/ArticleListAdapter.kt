package com.murakami.fankami.first

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.murakami.fankami.first.model.Article
import com.murakami.fankami.first.view.ArticleView

/**
 * Created by fankami on 西暦16/11/14.
 */

class ArticleListAdapter(private  val context: Context) : BaseAdapter() {
    var articles: List<Article> = emptyList()
    override fun getCount(): Int = articles.size
    override fun getItem(position: Int): Any? = articles[position]
    override fun getItemId(position: Int): Long = 0
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
            ((convertView as? ArticleView) ?: ArticleView(context)).apply {
                setArticle(articles[position])
            }
}