package kr.co.everex.realmtest.info

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.internal.Internal.instance

class MyApplication : Application() {

    init{
        instance = this
    }
    companion object {
        private var instance: MyApplication? = null
        fun getApplicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        // Realm 초기화
        Realm.init(this)
        val config : RealmConfiguration = RealmConfiguration.Builder()
            .name("RealmTest.realm") // 생성할 realm 파일 이름 지정
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(config)
    }

}