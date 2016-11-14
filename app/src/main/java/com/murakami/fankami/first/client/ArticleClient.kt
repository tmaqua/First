package com.murakami.fankami.first.client

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import com.murakami.fankami.first.model.Article

/**
 * Created by fankami on 西暦16/11/15.
 */

interface ArticleClient {

    @GET("/api/v2/items")
    fun search(@Query("query") query: String): Observable<List<Article>>
}