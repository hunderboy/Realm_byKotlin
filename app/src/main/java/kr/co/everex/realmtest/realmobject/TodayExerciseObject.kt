package kr.co.everex.realmtest.realmobject

import io.realm.Realm
import io.realm.RealmObject
import kr.co.everex.realmtest.TestObject

open class TodayExerciseObject : RealmObject() {
    lateinit var name: String
    lateinit var phone: String
    lateinit var email: String
}

/**
 * TestObject Realm객체의 관리자
 */
class TodayExerciseRealmManager(val realm: Realm) {
    /*** 맨처음에 찾은 데이터 추출 */
    fun find(name: String): TestObject? {
        return realm.where(TestObject::class.java).equalTo("name", name).findFirst()
    }
    /*** 데이터 모두 추출*/
    fun findAll(): List<TestObject> {
        return realm.where(TestObject::class.java).findAll()
    }
    /*** Realm 객체 생성*/
    fun create(curdata: TestObject) {
        realm.beginTransaction()

        val data = realm.createObject(TestObject::class.java)
        data.name = curdata.name
        data.phone = curdata.phone
        data.email = curdata.email

        realm.commitTransaction()
    }
    /*** Realm 객체 수정 */
    fun update(name: String, curdata: TestObject) {
        realm.beginTransaction()
        val data = find(name)
        data?.name = curdata.name
        data?.phone = curdata.phone
        data?.email = curdata.email

        realm.commitTransaction()
    }
    /*** Realm 객체 지정 삭제 */
    fun deleteByName(name: String) {
        realm.beginTransaction()
        val data = realm.where(TestObject::class.java).equalTo("name",name).findAll()
        data.deleteAllFromRealm()
        realm.commitTransaction()
    }
}

//    private fun deleteData() {
//
//        try {
//            val id: Long = edt_id.text.toString().toLong()
//            val dataModel =
//                realm!!.where(DataModel::class.java).equalTo("id", id).findFirst()
//            realm!!.executeTransaction {
//                dataModel?.deleteFromRealm()
//            }
//            clearFields()
//
//            Log.e("Status","Data deleted !!!")
//
//        }catch (e:Exception){
//            Log.e("Status","Something went Wrong !!!")
//        }
//    }
//    private fun updateData() {
//
//        try {
//
//            val id: Long = edt_id.text.toString().toLong()
//            val dataModel =
//                realm!!.where(DataModel::class.java).equalTo("id", id).findFirst()
//
//            realm!!.executeTransaction {
//                dataModel?.name = edt_name.text.toString()
//                dataModel?.email = edt_email.text.toString()
//            }
//
////            edt_name.setText(dataModel?.name)
////            edt_email.setText(dataModel?.email)
//
//            Log.e("Status","Data Fetched !!!")
//        }catch (e:Exception){
//            Log.e("Status","Something went Wrong !!!")
//        }
//    }
//    private fun findDataAboutID() {
//        try {
//            val id: Long = edt_id.text.toString().toLong()
//            val dataModel =
//                realm!!.where(DataModel::class.java).equalTo("id", id).findFirst()
//
//            Toast.makeText(this,
//                dataModel?.id.toString() +" , "+ dataModel?.name +" , "+ dataModel?.email, Toast.LENGTH_SHORT).show()
//
//            Log.e("찾은 데이터",dataModel?.id.toString() +" , "+ dataModel?.name +" , "+ dataModel?.email)
//            Log.e("Status","Data Fetched !!!")
//        } catch (e: Exception) {
//            Log.e("Status","Something went Wrong !!!")
//        }
//    }
