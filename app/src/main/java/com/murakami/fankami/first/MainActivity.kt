package com.murakami.fankami.first

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.murakami.fankami.first.model.Article
import com.murakami.fankami.first.model.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listAdapter = ArticleListAdapter(applicationContext)
        listAdapter.articles = dummyList()
        val listView: ListView = findViewById(R.id.listView) as ListView
        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val article = listAdapter.articles[position]
            ArticleActivity.intent(this, article).let { startActivity(it) }
        }
    }

    private fun dummyArticle(title: String, userName: String): Article =
            Article(id = "1",
                    title = title,
                    url = "www.gakufes.jp",
                    user = User(id = "100", name = userName, profileImageUrl = ""))

    private fun dummyList(): List<Article> =
        listOf<Article>( dummyArticle("a", "A"), dummyArticle("b", "B") )
}
