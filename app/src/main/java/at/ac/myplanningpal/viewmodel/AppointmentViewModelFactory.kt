package at.ac.myplanningpal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.ac.myplanningpal.repositories.AppointmentRepository
import at.ac.myplanningpal.repositories.NoteRepository
import java.lang.IllegalArgumentException

class AppointmentViewModelFactory(private val repository: AppointmentRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AppointmentViewModel::class.java)) {
            return AppointmentViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}