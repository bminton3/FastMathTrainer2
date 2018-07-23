package com.minton.fastmathtrainer.Scoring

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface ScoreTableDAO {

    @Query("SELECT * FROM ScoreTable")
    fun getAll(): List<ScoreTable>

    @Insert(onConflict = REPLACE)
    fun insert(scoreTable: ScoreTable)

    @Query("DELETE from ScoreTable")
    fun deleteAll()
}