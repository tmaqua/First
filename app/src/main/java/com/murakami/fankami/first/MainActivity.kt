package com.murakami.fankami.first

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.murakami.fankami.first.client.ArticleClient
import com.murakami.fankami.first.util.toast
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlin.properties.Delegates
import android.app.Activity
import android.util.Log
import com.murakami.fankami.first.model.Cat

class MainActivity : RxAppCompatActivity() {

    private var realm: Realm by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        realm = Realm.getDefaultInstance()

        val listAdapter = ArticleListAdapter(applicationContext)
        val progressBar = findViewById(R.id.progressBar)
        val listView: ListView = findViewById(R.id.listView) as ListView
        val queryEditText = findViewById(R.id.queryEditText) as EditText
        val searchButton = findViewById(R.id.searchButton) as Button

        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val intent = ArticleActivity.intent(this, listAdapter.articles[position])
            startActivity(intent)
        }

        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        val articleClient = retrofit.create(ArticleClient::class.java)

        searchButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progressBar.visibility = View.GONE
                    }
                    .bindToLifecycle(this)
                    .subscribe({
                        queryEditText.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                        saveCat()
                        showLog()
                    }, {
                        toast("error: $it")
                    })
        }
    }

    private fun saveCat() {
        realm.executeTransaction {
            val cat = realm.createObject(Cat::class.java)
            cat.name = "MYAAAA"
        }
    }

    private fun showLog() {
        realm.where(Cat::class.java).findAll().forEach {
            Log.d("REALM_DEBUG_LOG", it.name)
        }
    }

}
