package kr.co.everex.realmtest.realmobject

import io.realm.Realm
import io.realm.RealmObject
import io.realm.Sort
import kr.co.everex.realmtest.TestObject

open class TodayExerciseObject : RealmObject() {
    var name: String = "기본"
    var phone: String = "기본"
    var email: String = "기본"
    var exerciseDate: String = "0000-00-00"  // 운동 일자
}

/**
 * TestObject Realm객체의 관리자
 */
class TodayExerciseRealmManager(val realm: Realm) {
    /*** 맨처음에 찾은 데이터 추출 */
    fun find(name: String): TodayExerciseObject? {
        return realm.where(TodayExerciseObject::class.java).equalTo("name", name).findFirst()
    }
    /*** 데이터 모두 추출*/
    fun findAll(): List<TodayExerciseObject> {
        return realm.where(TodayExerciseObject::class.java).findAll()
    }
    // 순자 배열
    fun findAllbySorting() : List<TodayExerciseObject>{
        return realm.where(TodayExerciseObject::class.java).findAll()
                .sort("exerciseDate", Sort.DESCENDING)
    }
    /*** Realm 객체 생성*/
    fun create(todayText: String) {
        realm.beginTransaction()
        val data = realm.createObject(TodayExerciseObject::class.java)
//        data.name = curdata.name
//        data.phone = curdata.phone
//        data.email = curdata.email
        data.exerciseDate = todayText // 운동 일자
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

