package kr.co.everex.realmtest.realmobject

import io.realm.RealmModel
import io.realm.RealmObject
import java.util.*

open class PainInfo : RealmObject() {

    var year :Int = 2021  // 년
    var month :Int = 4    // 월
    var day :Int = 21     // 일
    var exerciseDay : String = "2021-04-21"

    var userID :String = "없음"          // 운동하는 사람 아이디
    var assignedProgram:String = "없음"  // 할당된 프로그램
    var painInfoBeforeExercise:Int = 0  // 운동 전 통증정보 (0~10)
    var painInfoAfterExercise:Int = 0   // 운동 후 통증정보 (0~10)

}
