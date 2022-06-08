package at.ac.myplanningpal.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.models.getAppointmentsFromModel
import com.himanshoe.kalendar.common.data.KalendarEvent
import java.time.LocalDate


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
        return getAppointmentsByDate(LocalDate.now())
    }

    fun getAppointmentsByDate(date: LocalDate): List<Appointment> {
        val returnList = mutableListOf<Appointment>()
        for (appointment in _appointments) {
            if (appointment.date == date) returnList.add(appointment)
        }
        return returnList
    }

    fun addAppointment(movie: Appointment) {
        _appointments.add(movie)
    }

    fun removeAppointment(movie: Appointment) {
        _appointments.remove(movie)
    }

    fun getCalendarEvents(): List<KalendarEvent> {
        val returnList = mutableListOf<KalendarEvent>()
        for (appointment in _appointments) {
            returnList.add(KalendarEvent(appointment.date, appointment.eventName, appointment.eventDescription))
        }
        return returnList
    }

    fun getDates(): List<LocalDate> {
        var dates = mutableListOf<LocalDate>()
        for (appointment in _appointments) {
            if (!dates.toString().contains(appointment.date.toString())) {
                dates.add(appointment.date)
            }
        }
        dates = dates.distinct().toMutableList()
        dates.sort()
        return dates
    }
}