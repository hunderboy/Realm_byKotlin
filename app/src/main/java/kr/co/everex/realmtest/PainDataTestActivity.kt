package kr.co.everex.realmtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.data_input.*
import kr.co.everex.realmtest.databinding.ActivityMainBinding
import kr.co.everex.realmtest.databinding.ActivityPainDataTestBinding
import kr.co.everex.realmtest.realmobject.DataModel
import kr.co.everex.realmtest.realmobject.RealmPainInfo

class PainDataTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPainDataTestBinding
    private val TAG = "PainDataTestActivity : "

    // 데이터 초기화
    var realm: Realm? = null
    private val dataModel = RealmPainInfo()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPainDataTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // 뷰 바인딩 적용 완료

        realm = Realm.getDefaultInstance()
        binding.btnInsertdata.setOnClickListener(){ addData() }
        binding.btnReaddata.setOnClickListener(){ readData() }
        binding.btnUpdatedata.setOnClickListener(){ updateData() }
        binding.btnDeletedata.setOnClickListener(){ deleteData() }
        binding.btnFindDataAboutID.setOnClickListener(){ findDataAboutID() }
    }

    fun deleteData() {
        try {
            val id: Long = edt_id.text.toString().toLong()
            val dataModel =
                realm!!.where(DataModel::class.java).equalTo("id", id).findFirst()
            realm!!.executeTransaction {
                dataModel?.deleteFromRealm()
            }
            clearFields()

            Log.e("Status","Data deleted !!!")

        }catch (e:Exception){
            Log.e("Status","Something went Wrong !!!")
        }
    }
    fun updateData() {
        try {

            val id: Long = edt_id.text.toString().toLong()
            val dataModel =
                realm!!.where(DataModel::class.java).equalTo("id", id).findFirst()

            realm!!.executeTransaction {
                dataModel?.name = edt_name.text.toString()
                dataModel?.email = edt_email.text.toString()
            }

//            edt_name.setText(dataModel?.name)
//            edt_email.setText(dataModel?.email)

            Log.e("Status","Data Fetched !!!")
        }catch (e:Exception){
            Log.e("Status","Something went Wrong !!!")
        }
    }
    fun addData() {
        try {
            dataModel.userID = binding.edtId.text.toString()
            dataModel.assignedProgram = binding.edtProgram.text.toString()
            dataModel.painInfoBeforeExercise = binding.edtBeforePain.text.toString().toInt()
            dataModel.painInfoAfterExercise = binding.edtAfterPain.text.toString().toInt()

            dataModel.year = binding.edtYear.text.toString().toInt()
            dataModel.month = binding.edtMonth.text.toString().toInt()
            dataModel.day = binding.edtDay.text.toString().toInt()

            dataModel.exerciseDay = binding.edtYear.text.toString()+"-"+binding.edtMonth.text.toString()+"-"+binding.edtDay.text.toString()

            realm!!.executeTransaction {
                    realm -> realm.copyToRealm(dataModel)
            }
            clearFields()
            Log.e(TAG,"통증 데이터 추가됨 !!!")
        }catch (e:Exception){
            Log.e("데이터 추가 뭐가 문제야?",e.toString())
        }
    }
    fun readData() {

        try {
            val dataModels: List<RealmPainInfo> = realm!!.where(RealmPainInfo::class.java).findAll()
            Log.e("indices",dataModels.indices.toString()) // 마지막 인덱스 번호?
            for (i in dataModels.indices) {

                Log.e("ReadData , index[$i]",
                    dataModels[i].exerciseDay +" , "+
                            dataModels[i].year.toString() +" , "+
                            dataModels[i].month.toString() +" , "+
                            dataModels[i].day.toString() +" , "+
                            dataModels[i].userID +" , "+
                            dataModels[i].assignedProgram +" , "+
                            dataModels[i].painInfoBeforeExercise +" , "+    // 운동 전
                            dataModels[i].painInfoAfterExercise             // 운동 후
                )
            }

            Log.e("Status","Data Fetched !!!")
        } catch (e: Exception) {
            Log.e("Status","Something went Wrong !!!")
        }
    }
    fun findDataAboutID() {
        /**
         * ID가 같은 것이 있으면, 먼저 저장되어있던 것을 뽑아낸다.
         */

        try {
            val id: Long = edt_id.text.toString().toLong()
            val dataModel =
                realm!!.where(DataModel::class.java).equalTo("id", id).findFirst()

            Toast.makeText(this,
                dataModel?.id.toString() +" , "+ dataModel?.name +" , "+ dataModel?.email, Toast.LENGTH_SHORT).show()

            Log.e("찾은 데이터",dataModel?.id.toString() +" , "+ dataModel?.name +" , "+ dataModel?.email)
            Log.e("Status","Data Fetched !!!")
        } catch (e: Exception) {
            Log.e("Status","Something went Wrong !!!")
        }
    }

    // 텍스트 클리어
    fun clearFields(){
        binding.edtBeforePain.setText("")
        binding.edtAfterPain.setText("")
        binding.edtDay.setText("")
    }
}