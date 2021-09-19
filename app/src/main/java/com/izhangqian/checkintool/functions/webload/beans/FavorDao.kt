package com.izhangqian.checkintool.functions.webload.beans

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavorDao {
    @Query("select * from t_favor where owner = 'zq'")
    fun getAllFavors() : LiveData<MutableList<FavorArticle>>

    @Query("select * from t_favor where owner = 'zl'")
    fun getZLFavors() : LiveData<MutableList<FavorArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneFavor(favorArticle: FavorArticle) : Long
}