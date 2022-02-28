package com.example.lecture4notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_note_details.*
import java.util.*

val INTENT_PARAMETER_EDIT_NOTE: String = "editNote"
val INTENT_PARAMETER_NOTE_POSITION: String = "position"

class NoteDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

        intent = getIntent()
        val extras = intent.extras
        if(extras != null && extras.containsKey(INTENT_PARAMETER_EDIT_NOTE)){
            val oldNote: Note = extras[INTENT_PARAMETER_EDIT_NOTE] as Note
            val position: Int = extras[INTENT_PARAMETER_NOTE_POSITION] as Int
            noteTitleTextViewId.setText(oldNote.noteTitle)
            noteDetailsTextId.setText(oldNote.noteDescription)


            saveNoteUpdateButtonId.setOnClickListener {
                NoteRepsoitory.listOfNotes.removeAt(position)
                val newNote = getNoteFromFields()
                NoteRepsoitory.listOfNotes.add(position, newNote)
                setResult(RESULT_OK)
                finish()
            }

        }
        else {
            saveNoteUpdateButtonId.setOnClickListener {
                val note = getNoteFromFields()
                NoteRepsoitory.listOfNotes.add(note)
                finish()
            }
        }
    }

    private fun getNoteFromFields(): Note{
        val note = Note()
        note.noteDate = Date()
        note.noteTitle = noteTitleTextViewId.text.toString()
        note.noteDescription = noteDetailsTextId.text.toString()
        return note
    }
}