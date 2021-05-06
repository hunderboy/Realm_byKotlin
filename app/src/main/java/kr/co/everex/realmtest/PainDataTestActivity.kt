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
import kr.co.everex.realmtest.realmobject.PainInfo
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
            val dataModel =
                realm!!.where(RealmPainInfo::class.java).equalTo("realmObjectType", "RealmPainInfo").findFirst()

            Log.e("painInfoList 사이즈 = ",dataModel?.painInfoList?.size.toString())

            val data = PainInfo()
            data.year = binding.edtYear.text.toString().toInt()
            data.month = binding.edtMonth.text.toString().toInt()
            data.day = binding.edtDay.text.toString().toInt()
            data.exerciseDay = binding.edtYear.text.toString()+"-"+binding.edtMonth.text.toString()+"-"+binding.edtDay.text.toString()

            data.userID = binding.edtId.text.toString()
            data.assignedProgram = binding.edtProgram.text.toString()
            data.painInfoBeforeExercise = binding.edtBeforePain.text.toString().toInt()
            data.painInfoAfterExercise = binding.edtAfterPain.text.toString().toInt()

            realm!!.executeTransaction {
                dataModel?.painInfoList?.add(data) // 데이터 추가
            }


            Log.e("Status","Data Fetched !!!")
        }catch (e:Exception){
            Log.e("Status","Something went Wrong !!!")
        }
    }
    /**
     * 최초에 첫 통증 데이터 추가 시에만 실행되어야 함
     * 그다은 통증 데이터 추가 시에는 모두 Update
     */
    fun addData() {
        try {
            val data = PainInfo()
            data.year = binding.edtYear.text.toString().toInt()
            data.month = binding.edtMonth.text.toString().toInt()
            data.day = binding.edtDay.text.toString().toInt()
            data.exerciseDay = binding.edtYear.text.toString()+"-"+binding.edtMonth.text.toString()+"-"+binding.edtDay.text.toString()

            data.userID = binding.edtId.text.toString()
            data.assignedProgram = binding.edtProgram.text.toString()
            data.painInfoBeforeExercise = binding.edtBeforePain.text.toString().toInt()
            data.painInfoAfterExercise = binding.edtAfterPain.text.toString().toInt()

            dataModel.painInfoList.add(data)
            Log.e("painInfoList 사이즈 = ",dataModel.painInfoList.size.toString())

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
//            Log.e("indices",dataModels.indices.toString()) // 마지막 인덱스 번호?
//            for (i in dataModels.indices) {
//                Log.e("ReadData , index[$i]",
//                    dataModels[i].exerciseDay +" , "+
//                            dataModels[i].year.toString() +" , "+
//                            dataModels[i].month.toString() +" , "+
//                            dataModels[i].day.toString() +" , "+
//                            dataModels[i].userID +" , "+
//                            dataModels[i].assignedProgram +" , "+
//                            dataModels[i].painInfoBeforeExercise +" , "+    // 운동 전
//                            dataModels[i].painInfoAfterExercise             // 운동 후
//                )
//            }

            val dataList = dataModels[0].painInfoList.reversed()
            Log.e("dataList",dataList.size.toString())
            for (i in dataList.indices) {
                Log.e("ReadData , index[$i]",
                    dataList[i]?.exerciseDay +" , "+
                            dataList[i]?.year.toString() +" , "+
                            dataList[i]?.month.toString() +" , "+
                            dataList[i]?.day.toString() +" , "+
                            dataList[i]?.userID +" , "+
                            dataList[i]?.assignedProgram +" , "+
                            dataList[i]?.painInfoBeforeExercise +" , "+    // 운동 전
                            dataList[i]?.painInfoAfterExercise             // 운동 후
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