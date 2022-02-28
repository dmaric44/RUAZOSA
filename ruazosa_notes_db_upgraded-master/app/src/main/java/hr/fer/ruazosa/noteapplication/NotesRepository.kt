package hr.fer.ruazosa.noteapplication

import android.app.Application
import androidx.lifecycle.LiveData

class NotesRepository {

    var noteDao: NotesDao? = null
    var listOfAllNotes: LiveData<List<Note>>? = null

    init {
    }

    constructor(application: Application) {
        val database = NotesRoomDatabase.getDatabase(application)
        noteDao = database?.notesDao()
    }

    fun insertNote(note: Note) {
        noteDao?.insertAll(note)
    }
    fun getAllNote(): List<Note>? {
        return noteDao?.getAll()
    }

    fun deleteNote(note: Note){
        noteDao?.delete(note)
    }

    fun updateNote(note: Note){
        noteDao?.updateNote(note.noteDescription!!, note.noteTitle!!, note.noteId!!)
    }
}