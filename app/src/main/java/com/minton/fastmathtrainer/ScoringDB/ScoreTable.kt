//package com.minton.fastmathtrainer.ScoringDB
//
//import android.arch.persistence.room.ColumnInfo
//import android.arch.persistence.room.Entity
//import android.arch.persistence.room.PrimaryKey
//
//@Entity
//data class ScoreTable(@PrimaryKey(autoGenerate = true) var id : Long?,
//                      @ColumnInfo(name = "score") var score : Int,
//                      @ColumnInfo(name = "text") var text : String,
//                      @ColumnInfo(name = "gametype") var gametype : String,
//                      @ColumnInfo(name = "difficulty") var difficulty : String,
//                      @ColumnInfo(name = "timestamp") var timestamp : Long) {
//    constructor():this(null, 0, "", "", "", 0)
//
//}