package at.ac.myplanningpal.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.models.Note

import at.ac.myplanningpal.repositories.AppointmentRepository
import at.ac.myplanningpal.repositories.NoteRepository
import com.himanshoe.kalendar.common.data.KalendarEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class AppointmentViewModel(
    private val repository: AppointmentRepository
) : ViewModel() {
    private var _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments = _appointments.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllAppointments().collect { listOfAppointments ->
                _appointments.value = listOfAppointments
            }
        }
    }

//    fun getAppointments(): List<Appointment> {
//        return appointments
//    }
//
//
//    suspend fun getTodaysAppointments(): List<Appointment> {
//        return getAppointmentsByDate(LocalDate.now())
//    }
//
//    suspend fun getAppointmentsByDate(date: LocalDate): List<Appointment> {
//        val returnList = mutableListOf<Appointment>()
//        val allAppointments: List<Appointment>
//        appointments.collect { items ->
//            val allAppointments = items
//            for (appointment in allAppointments) {
//                if (appointment.date == date.toString()) returnList.add(appointment)
//            }
//        }
//
//        return returnList
//    }

    fun addAppointment(appointment: Appointment) {
        repository.addAppointment(appointment = appointment)
    }

    fun removeAppointment(appointment: Appointment) {
        repository.deleteAppointment(appointment = appointment)
    }

//    fun getCalendarEvents(): List<KalendarEvent> {
//        val returnList = mutableListOf<KalendarEvent>()
//        for (appointment in _appointments) {
//            returnList.add(KalendarEvent(appointment.date, appointment.eventName, appointment.eventDescription))
//        }
//        return returnList
//    }
//
//    fun getDates(): List<LocalDate> {
//        var dates = mutableListOf<LocalDate>()
//        for (appointment in _appointments) {
//            if (!dates.toString().contains(appointment.date.toString())) {
//                dates.add(appointment.date)
//            }
//        }
//        dates = dates.distinct().toMutableList()
//        dates.sort()
//        return dates
//    }
}
