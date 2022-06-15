package at.ac.myplanningpal.db

import androidx.room.*
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentsDao {

    @Insert
    suspend fun addAppointment(appointment: Appointment)

    @Update
    suspend fun editAppointment(appointment: Appointment)

    @Delete
    suspend fun deleteAppointment(appointment: Appointment)

    @Query("SELECT * FROM appointments")
    fun getAppointments(): Flow<List<Appointment>>

    @Query("SELECT * FROM appointments WHERE id=:id")
    fun getAppointmentById(id: Long): Appointment
}