package com.izhangqian.checkintool.newdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FunctionDao {
    @Query("select * from t_function")
    fun getAllFuntion() : LiveData<MutableList<FunctionItemBean>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneItem(function: FunctionItemBean) : Long
}