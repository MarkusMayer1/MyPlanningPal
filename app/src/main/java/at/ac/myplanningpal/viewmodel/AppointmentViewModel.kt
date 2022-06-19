package at.ac.myplanningpal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.repositories.AppointmentRepository
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

    fun addAppointment(appointment: Appointment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAppointment(appointment = appointment)
        }
    }

    fun removeAppointment(appointment: Appointment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAppointment(appointment = appointment)
        }
    }

    fun editAppointment(appointment: Appointment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.editAppointment(appointment = appointment)
        }
    }

    fun getCalendarEvents(): List<KalendarEvent> {
        val returnList = mutableListOf<KalendarEvent>()
        for (appointment in _appointments.value) {
            try {
                val datess = LocalDate.parse(appointment.date, DateTimeFormatter.ISO_LOCAL_DATE)
                returnList.add(KalendarEvent(datess, appointment.eventName, appointment.eventDescription))
            } catch(e: Exception) {
                Log.d("LocalDate parsing error", e.message.toString())
            }
        }
        return returnList
    }

    fun getAppointments(): List<Appointment> {
        var result: List<Appointment> = listOf()
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllAppointments().collect { listOfAppointments ->
                result = listOfAppointments
            }
        }
        return result
    }
}
