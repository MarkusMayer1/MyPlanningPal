package at.ac.myplanningpal.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentsDao {

    @Insert
    fun addAppointment(appointment: Appointment)

    @Delete
    fun deleteAppointment(appointment: Appointment)

    @Query("SELECT * FROM appointments")
    fun getAppointments(): Flow<List<Appointment>>

    @Query("SELECT * FROM appointments WHERE id=:id")
    fun getAppointmentById(id: Long): Appointment
}