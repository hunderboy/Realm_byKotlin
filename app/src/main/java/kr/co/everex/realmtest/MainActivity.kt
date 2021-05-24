package kr.co.everex.realmtest

import android.content.Intent
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

        binding.button1.setOnClickListener{
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener{
            val intent = Intent(this, TodayExerciseActivity::class.java)
            startActivity(intent)
        }
        binding.button3.setOnClickListener{
            val intent = Intent(this, PainDataTestActivity::class.java)
            startActivity(intent)
        }
        binding.button4.setOnClickListener{
            val intent = Intent(this, RealmAsyncTestActivity::class.java)
            startActivity(intent)
        }



    }


}