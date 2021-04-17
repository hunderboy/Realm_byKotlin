package kr.co.everex.realmtest.info

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {



    override fun onCreate() {
        super.onCreate()

        // Realm 초기화
        Realm.init(this)
//        val config : RealmConfiguration = RealmConfiguration.Builder()
//            .name("RealmTest.realm") // 생성할 realm 파일 이름 지정
//            .deleteRealmIfMigrationNeeded()
//            .build()
//
//        Realm.setDefaultConfiguration(config)
    }

}