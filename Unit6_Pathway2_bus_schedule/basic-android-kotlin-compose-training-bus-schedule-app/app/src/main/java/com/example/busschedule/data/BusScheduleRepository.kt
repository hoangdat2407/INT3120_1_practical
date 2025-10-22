package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [BusSchedule] from a given data source.
 */
interface BusSchedulesRepository {
    /**
     * Retrieve all the BusSchedules from the the given data source.
     */
    fun getAllBusSchedulesStream(): Flow<List<BusSchedule>>

    /**
     * Retrieve an BusSchedule from the given data source that matches with the [id].
     */
    fun getBusScheduleStream(stopName: String)

    /**
     * Insert BusSchedule in the data source
     */
    suspend fun insertBusSchedule(BusSchedule: BusSchedule)

    /**
     * Delete BusSchedule from the data source
     */
    suspend fun deleteBusSchedule(BusSchedule: BusSchedule)

    /**
     * Update BusSchedule in the data source
     */
    suspend fun updateBusSchedule(BusSchedule: BusSchedule)
}