package com.example.newlocation02.entity.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newlocation02.entity.MyHomeWork
import com.example.newlocation02.entity.dao.IMyHomeWorkDao

@Database(entities = [MyHomeWork::class],version = 2,exportSchema = false)
abstract class HomeWorkDataBase : RoomDatabase() {
    abstract fun getHomeWork():IMyHomeWorkDao

}