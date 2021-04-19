package kr.co.everex.realmtest.realmobject

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.json.JSONObject


open class DataModel : RealmObject() {
    @PrimaryKey
    var id = 0
    var name: String? = null
    var email: String? = null

    var jsonObject : JSONObject? = null

}