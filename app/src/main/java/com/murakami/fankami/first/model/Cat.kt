package com.murakami.fankami.first.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

/**
 * Created by fankami on 西暦16/11/16.
 */

open class Cat : RealmObject() {
    open var name: String? = null
}
