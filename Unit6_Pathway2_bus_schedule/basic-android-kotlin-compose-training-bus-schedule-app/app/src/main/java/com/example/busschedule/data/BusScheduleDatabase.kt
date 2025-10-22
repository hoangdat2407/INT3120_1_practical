package com.example.busschedule.data

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class BusScheduleDatabase : RoomDatabase() {
    abstract fun busScheduleDao(): BusScheduleDAO
    companion object {
        @Volatile
        private var Instance: BusScheduleDatabase? = null

        fun getDatabase(context: Context): BusScheduleDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BusScheduleDatabase::class.java, "bus_schedule.db")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}