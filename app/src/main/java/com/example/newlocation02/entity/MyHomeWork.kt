package com.example.newlocation02.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_work")
data class MyHomeWork(@PrimaryKey(autoGenerate = false)
                        @ColumnInfo(name = "id")
                      var id:Int,
                      @ColumnInfo(name = "name")
                      var name:String,
                      @ColumnInfo(name = "time")
                      var time:String) {
}