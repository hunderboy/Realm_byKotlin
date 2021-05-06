package kr.co.everex.realmtest.realmobject

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmPainInfo  : RealmObject() {
//    @PrimaryKey
//    var dateOfExercise : Date = "RealmPainInfo"
//    var assignedWeek:String = "없음"         // 할당된 주차
//    var assignedDay:String = "없음"          // 할당된 일자


//    var exerciseDay : String = "2021-04-21"
//    var date : Date = Date()
//
//    var year :Int = 2021  // 년
//    var month :Int = 4    // 월
//    var day :Int = 21     // 일
//    var userID :String = "없음"              // 운동하는 사람 아이디
//    var assignedProgram:String = "없음"      // 할당된 프로그램
//    var painInfoBeforeExercise:Int = 0  // 운동 전 통증정보 (0~10)
//    var painInfoAfterExercise:Int = 0   // 운동 후 통증정보 (0~10)


    @PrimaryKey
    var realmObjectType : String = "RealmPainInfo"
    var painInfoList : RealmList<PainInfo> = RealmList()


}