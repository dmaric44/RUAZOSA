package hr.fer.ruazosa.noteapplication


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_notes_details.*
import java.util.*

class NotesDetails : AppCompatActivity() {
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_details)

        viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                NotesViewModel::class.java)
        viewModel.initializeRepository(application)

        intent = getIntent()
        val extras = intent.extras
        var note: Note? = null
        if(extras != null){
            note = extras["note"] as Note
            noteTitleEditText.setText(note.noteTitle)
            noteDescriptionEditText.setText(note.noteDescription)

            saveNoteButton.setOnClickListener {
                note.noteTitle = noteTitleEditText.text.toString()
                note.noteDescription = noteDescriptionEditText.text.toString()
                viewModel.updateNote(note)
                finish()
            }

        }
        else {
            saveNoteButton.setOnClickListener {
                val note = Note()
                note.noteId = null
                note.noteTitle = noteTitleEditText.text.toString()
                note.noteDescription = noteDescriptionEditText.text.toString()
                note.noteDate = Date()
                viewModel.insertNote(note)
                finish()
            }
        }
    }


}
