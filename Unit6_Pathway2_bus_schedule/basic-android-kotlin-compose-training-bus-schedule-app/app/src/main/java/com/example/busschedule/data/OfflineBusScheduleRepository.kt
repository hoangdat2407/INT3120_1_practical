package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

// tao mot kho offline voi interface la ... nhan vao la mot objec dao de goi den csdl
class OfflineBusScheduleRepository(private val BusScheduleDAO: BusScheduleDAO) : BusSchedulesRepository {
    /**
     * Retrieve all the BusSchedules from the the given data source.
     */
    override fun getAllBusSchedulesStream(): Flow<List<BusSchedule>> = BusScheduleDAO.getAllItems()


    /**
     * Retrieve an BusSchedule from the given data source that matches with the [id].
     */
    override fun getBusScheduleStream(stopName: String): Flow<List<BusSchedule>> = BusScheduleDAO.getBusScheduleStream(stopName)


    /**
     * Insert BusSchedule in the data source
     */
    override suspend fun insertBusSchedule(BusSchedule: BusSchedule) = BusScheduleDAO.insert(BusSchedule)


    /**
     * Delete BusSchedule from the data source
     */
    override suspend fun deleteBusSchedule(BusSchedule: BusSchedule) = BusScheduleDAO.delete(BusSchedule)



    /**
     * Update BusSchedule in the data source
     */
    override suspend fun updateBusSchedule(BusSchedule: BusSchedule) = BusScheduleDAO.update(BusSchedule)

}