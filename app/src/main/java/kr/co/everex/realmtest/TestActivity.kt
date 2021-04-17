package kr.co.everex.realmtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.data_input.*
import kr.co.everex.realmtest.databinding.ActivityMainBinding
import kr.co.everex.realmtest.databinding.ActivityTestBinding
import kr.co.everex.realmtest.realmobject.DataModel

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding

    // 데이터 초기화
    var realm: Realm? = null
    private val dataModel = DataModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // 뷰 바인딩 적용 완료

        realm = Realm.getDefaultInstance()
        binding.btnInsertdata.setOnClickListener(){ addData() }
        binding.btnReaddata.setOnClickListener(){ readData() }
        binding.btnUpdatedata.setOnClickListener(){ updateData() }
        binding.btnDeletedata.setOnClickListener(){ deleteData() }
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

            edt_name.setText(dataModel?.name)
            edt_email.setText(dataModel?.email)

            Log.e("Status","Data Fetched !!!")
        }catch (e:Exception){
            Log.e("Status","Something went Wrong !!!")
        }
    }

    fun addData() {

        try {

            dataModel.id = edt_id.text.toString().toInt()
            dataModel.name = edt_name.text.toString()
            dataModel.email = edt_email.text.toString()

            realm!!.executeTransaction { realm -> realm.copyToRealm(dataModel) }

            clearFields()

            Log.e("Status","Data Inserted !!!")

        }catch (e:Exception){
            Log.e("Status","Something went Wrong !!!")
        }
    }

    fun readData() {

        try {

            val dataModels: List<DataModel> =
                realm!!.where(DataModel::class.java).findAll()

            for (i in dataModels.indices) {
                edt_id?.setText("" + dataModels[i].id)
                edt_name?.setText(dataModels[i].name)
                edt_email?.setText(dataModels[i].email)
                Log.e("readData",
                    dataModels[i].id.toString() + dataModels[i].name + dataModels[i].email)
            }

            Log.e("Status","Data Fetched !!!")

        } catch (e: Exception) {
            Log.e("Status","Something went Wrong !!!")
        }
    }

    fun clearFields(){

        edt_id.setText("")
        edt_name.setText("")
        edt_email.setText("")
    }


}