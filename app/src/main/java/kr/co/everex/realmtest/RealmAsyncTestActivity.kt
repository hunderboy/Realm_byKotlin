package kr.co.everex.realmtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import java.util.concurrent.CountDownLatch

class RealmAsyncTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm_async_test)



        // 테스트 코드
        val thread = HandlerThread("test")
        thread.start()
        val handler = Handler(thread.looper)
        val latch = CountDownLatch(1)
        handler.post {
            Log.e("deleteRealmUser","1")
            realm.executeTransactionAsync({
                val dataToDelete = findRealmUser()
                dataToDelete?.deleteFromRealm()
                Log.e("deleteRealmUser","2")
            }, {
                Log.e("deleteRealmUser","3-1")
                Log.v("EXAMPLE", "Successfully completed the transaction")
                result = true
            }, { error ->
                Log.e("deleteRealmUser","3-2")
                Log.e("EXAMPLE", "Failed the transaction: $error")
                result = false
            })
            latch.countDown()
        }
        latch.await()
    }
}