//package com.minton.fastmathtrainer.ScoringDB
//
//import android.arch.persistence.room.Database
//import android.arch.persistence.room.Room
//import android.arch.persistence.room.RoomDatabase
//import android.content.Context
//
//@Database(entities = arrayOf(ScoreTable::class), version = 1)
//abstract class GameDatabase : RoomDatabase() {
//
//    abstract fun scoreTableDao() : ScoreTableDAO
//
//    companion object {
//        private var INSTANCE: GameDatabase? = null
//
//        fun getInstance(context: Context): GameDatabase? {
//            if (INSTANCE == null) {
//                synchronized(GameDatabase::class) {
//                    INSTANCE = Room.databaseBuilder(context.applicationContext, GameDatabase::class.java,
//                            "game.db").build()
//                }
//            }
//            return INSTANCE
//        }
//
//        fun destroyInstance() {
//            INSTANCE = null;
//        }
//    }
//}