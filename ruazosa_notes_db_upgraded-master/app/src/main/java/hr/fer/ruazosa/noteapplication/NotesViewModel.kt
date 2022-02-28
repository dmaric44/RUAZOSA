package hr.fer.ruazosa.noteapplication

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.FieldPosition


class NotesViewModel: ViewModel() {
    var repository: NotesRepository? = null
    var allNotes: MutableLiveData<List<Note>>  = MutableLiveData<List<Note>>()

    fun initializeRepository(application: Application) {
        repository = NotesRepository(application)
        getNotes()
    }

    fun deleteNote(position: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var notesList = allNotes.value
                var note = notesList?.get(position)
                if(note != null) {
                    repository?.deleteNote(note)
                }
                notesList = repository?.noteDao?.getAll()
                withContext(Dispatchers.Main) {
                    allNotes.value = notesList
                }
            }
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository?.updateNote(note)
            }
        }
    }

    fun getNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val notesList = repository?.noteDao?.getAll()
                withContext(Dispatchers.Main) {
                    allNotes.value = notesList
                }
            }
        }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository?.noteDao?.insertAll(note)

            }
        }
    }

}