package com.murakami.fankami.first

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.murakami.fankami.first.model.Article
import com.murakami.fankami.first.view.ArticleView

/**
 * Created by fankami on 西暦16/11/14.
 */

class ArticleActivity : AppCompatActivity() {

    companion object {
        private const val ARTICLE_EXTRA: String = "article"
        fun intent(context: Context, article: Article): Intent =
                Intent(context, ArticleActivity::class.java).putExtra(ARTICLE_EXTRA, article)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val articleView = findViewById(R.id.articleView) as ArticleView
        val webView = findViewById(R.id.webView) as WebView
        val article: Article = intent.getParcelableExtra(ARTICLE_EXTRA)
        articleView.setArticle(article)
        webView.loadUrl(article.url)
    }
}