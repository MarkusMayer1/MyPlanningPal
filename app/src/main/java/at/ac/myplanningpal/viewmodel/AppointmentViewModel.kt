package at.ac.myplanningpal.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.models.getAppointmentsFromModel
import com.himanshoe.kalendar.common.data.KalendarEvent
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class AppointmentViewModel : ViewModel() {
    //private val _appointments = mutableStateListOf<Appointment>()

    private var _appointments = mutableStateListOf<Appointment>()
    init {
        for (appointment in getAppointmentsFromModel().toMutableStateList()) {
            addAppointment(appointment)
        }
    }

    fun getAppointments(): List<Appointment> {
        return _appointments
    }

    fun getTodaysAppointments(): List<Appointment> {
        return getAppointmentsByDate(LocalDate.now().toString())
    }

    fun getAppointmentsByDate(date: String): List<Appointment> {
        val returnList = mutableListOf<Appointment>()
        for (appointment in _appointments) {
            if (appointment.date == date) returnList.add(appointment)
        }
        return returnList
    }

    fun addAppointment(appointment: Appointment) {
        _appointments.add(appointment)
    }

    fun removeAppointment(appointment: Appointment) {
        _appointments.remove(appointment)
    }

    fun getCalendarEvents(): List<KalendarEvent> {
        val returnList = mutableListOf<KalendarEvent>()
        for (appointment in _appointments) {
            val date = LocalDate.parse(appointment.date, DateTimeFormatter.ISO_LOCAL_DATE)
            returnList.add(KalendarEvent(date, appointment.eventName, appointment.eventDescription))
        }
        return returnList
    }

    fun getDates(): List<String> {
        var dates = mutableListOf<String>()
        for (appointment in _appointments) {
            if (!dates.contains(appointment.date)) {
                dates.add(appointment.date)
            }
        }
        dates = dates.distinct().toMutableList()
        dates.sort()
        return dates
    }
}