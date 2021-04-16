package kr.co.everex.realmtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.everex.realmtest.databinding.ActivityMainBinding
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // 뷰 바인딩 적용 완료


    }


}