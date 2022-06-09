package at.ac.myplanningpal.repositories

import at.ac.myplanningpal.db.AppointmentsDao
import at.ac.myplanningpal.db.NotesDao
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.models.Note
import kotlinx.coroutines.flow.Flow

class AppointmentRepository(private val dao: AppointmentsDao)
{
    fun addAppointment(appointment: Appointment) = dao.addAppointment(appointment = appointment)

    fun deleteAppointment(appointment: Appointment) = dao.deleteAppointment(appointment = appointment)

    fun getAllAppointments(): Flow<List<Appointment>> = dao.getAppointments()
}