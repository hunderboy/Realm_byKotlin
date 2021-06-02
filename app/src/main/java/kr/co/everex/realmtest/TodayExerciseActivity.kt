package kr.co.everex.realmtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import kotlinx.android.synthetic.main.data_input.*
import kr.co.everex.realmtest.databinding.ActivityTodayExerciseBinding
import kr.co.everex.realmtest.realmobject.TodayExerciseRealmManager

class TodayExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodayExerciseBinding
    val realm = Realm.getDefaultInstance()


    /**
     1. 2주차 정도의 데이터를 넣어 놓고 추출을 확인해야 한다.
     2. 데이터 추출후 필터 돌리기
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodayExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // 뷰 바인딩 적용 완료

        binding.btnInsertdata.setOnClickListener(){
            // 객체 생성
            TodayExerciseRealmManager(realm).create(binding.edtExerciseDay.text.toString())
        }
        binding.btnReaddata.setOnClickListener(){
            // 순차적으로 불러오기
            val dataModels = TodayExerciseRealmManager(realm).findAllbySorting()
            for (i in dataModels.indices) {
                Log.e("ReadData 순차적 , index[$i]", dataModels[i].exerciseDate)
            }
        }
        binding.btnFindDataAboutID.setOnClickListener(){
            // 모두 불러오기
            val dataModels = TodayExerciseRealmManager(realm).findAll()
            for (i in dataModels.indices) {
                Log.e("그냥 모두 찾기 , index[$i]", dataModels[i].exerciseDate)
            }
        }

    }


    // 텍스트 클리어
    fun clearFields(){
        binding.edtExerciseDay.setText("")
        binding.edtWeek.setText("")
        binding.edtDay.setText("")
        binding.edtRate.setText("")
//        edt_id.setText("")
//        edt_name.setText("")
//        edt_email.setText("")
    }

}