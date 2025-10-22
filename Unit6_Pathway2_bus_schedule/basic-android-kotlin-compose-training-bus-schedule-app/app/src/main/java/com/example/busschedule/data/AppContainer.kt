package com.example.busschedule.data

import android.content.Context



interface AppContainer {
    val busScheduleDAO: BusScheduleDAO
}
class AppDataContainer(context: Context) : AppContainer {
     override val busScheduleDAO: BusScheduleDAO by lazy {
        BusScheduleDatabase.getDatabase(context).busScheduleDao()
    }
}