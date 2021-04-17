package kr.co.everex.realmtest

import io.realm.Realm
import io.realm.RealmObject


open class TestObject : RealmObject() {
    lateinit var name: String
    lateinit var phone: String
    lateinit var email: String

    override fun toString(): String {
        return "이름:"+name+'\n'+"전화번호:"+phone+'\n'+"이메일:"+email+'\n'
    }

}


class RealmManager(val realm: Realm) {

    fun find(name: String): TestObject? {
        return realm.where(TestObject::class.java).equalTo("name", name).findFirst()
    }

    fun findAll(): List<TestObject> {
        return realm.where(TestObject::class.java).findAll()
    }

    fun create(curdata: TestObject) {
        realm.beginTransaction()

        val data = realm.createObject(TestObject::class.java)
        data.name = curdata.name
        data.phone = curdata.phone
        data.email = curdata.email

        realm.commitTransaction()
    }

    fun update(name: String, curdata: TestObject) {
        realm.beginTransaction()
        val data = find(name)
        data?.name = curdata.name
        data?.phone = curdata.phone
        data?.email = curdata.email

        realm.commitTransaction()
    }

    fun deleteByName(name: String) {
        realm.beginTransaction()
        val data = realm.where(TestObject::class.java).equalTo("name",name).findAll()
        data.deleteAllFromRealm()
        realm.commitTransaction()
    }
}
