package kr.co.everex.realmtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.data_input.*
import kr.co.everex.realmtest.databinding.ActivityMainBinding
import kr.co.everex.realmtest.databinding.ActivityTestBinding
import kr.co.everex.realmtest.databinding.ActivityTodayExerciseBinding
import kr.co.everex.realmtest.realmobject.DataModel

class TodayExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodayExerciseBinding

    /**
     1. 2주차 정도의 데이터를 넣어 놓고 추출을 확인해야 한다.
     2. 데이터 추출후 필터 돌리기
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodayExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // 뷰 바인딩 적용 완료




    }

    private fun addData() {
//        realm?.toString()?.let { Log.e("realm 상태", it) }

        try {

            dataModel.id = edt_id.text.toString().toInt()
            dataModel.name = edt_name.text.toString()
            dataModel.email = edt_email.text.toString()

            realm!!.executeTransaction {
                    realm -> realm.copyToRealm(dataModel)
            }

            clearFields()

            Log.e("Status","Data Inserted !!!")

        }catch (e:Exception){
            Log.e("뭐가 문제야?",e.toString())
            Log.e("Status","Something went Wrong !!!")
        }

    }
    private fun readData() {

        try {
            val dataModels: List<DataModel> = realm!!.where(DataModel::class.java).findAll()

            for (i in dataModels.indices) {
                edt_id?.setText("" + dataModels[i].id)
                edt_name?.setText(dataModels[i].name)
                edt_email?.setText(dataModels[i].email)
                Log.e("ReadData , index[$i]",
                    dataModels[i].id.toString() +" , "+ dataModels[i].name +" , "+ dataModels[i].email)
            }

            Log.e("Status","Data Fetched !!!")
        } catch (e: Exception) {
            Log.e("Status","Something went Wrong !!!")
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