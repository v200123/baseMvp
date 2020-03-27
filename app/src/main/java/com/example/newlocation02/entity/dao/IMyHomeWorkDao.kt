package com.example.newlocation02.entity.dao

import androidx.room.*
import com.example.newlocation02.entity.MyHomeWork

@Dao
interface IMyHomeWorkDao {
    @Insert
    fun inserHomeWork(myHomeWork: MyHomeWork)
    @Update
    fun updateHomeWork(myHomeWork: MyHomeWork)
    @Delete
    fun delectHomeWork(myHomeWork: MyHomeWork)
    @Query("DELETE FROM home_work")
    fun deleteAll()
//    @Query("SELECT * FROM MyHomeWork Order BY ID desc")
//   public fun getAllHomeWork():MutableList<MyHomeWork>
}