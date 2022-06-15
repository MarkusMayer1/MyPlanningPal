package at.ac.myplanningpal.repositories

import at.ac.myplanningpal.db.AppointmentsDao
import at.ac.myplanningpal.db.NotesDao
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.models.Note
import kotlinx.coroutines.flow.Flow

class AppointmentRepository(private val dao: AppointmentsDao)
{
    suspend fun addAppointment(appointment: Appointment) = dao.addAppointment(appointment = appointment)

    suspend fun editAppointment(appointment: Appointment) = dao.editAppointment(appointment = appointment)

    suspend fun deleteAppointment(appointment: Appointment) = dao.deleteAppointment(appointment = appointment)

    fun getAllAppointments(): Flow<List<Appointment>> = dao.getAppointments()
}