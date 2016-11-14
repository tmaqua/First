package com.murakami.fankami.first.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.murakami.fankami.first.R
import com.murakami.fankami.first.model.Article
import com.murakami.fankami.first.util.bindView

/**
 * Created by fankami on 西暦16/11/14.
 */

class ArticleView : FrameLayout {
    constructor(context: Context?): super(context)
    constructor(context: Context?,
                attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    val profileImageView: ImageView by bindView(R.id.profileImageView)
    val titleTextView: TextView by bindView(R.id.titleTextView)
    val userNameTextView: TextView by bindView(R.id.userNameTextView)


    init {
        LayoutInflater.from(context).inflate(R.layout.view_article, this)
    }

    fun setArticle(article: Article) {
        titleTextView?.text = article.title
        userNameTextView?.text = article.user.name

        // TODO プロフィール画像のセット
        profileImageView?.setBackgroundColor(Color.RED)
    }
}