package com.example.lecture4notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

val REQUEST_EDIT: Int = 10

class MainActivity : AppCompatActivity(), IOnItemClick {
    val noteAdapter: NotesAdapter = NotesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val note = Note()
        note.noteDate = Date()
        note.noteTitle = "Naslov"
        note.noteDescription = "Prva bilješka"

        NoteRepsoitory.listOfNotes.add(note)

        notesRecycleViewId.layoutManager = LinearLayoutManager(this)
        notesRecycleViewId.adapter = noteAdapter

        floatingActionButton.setOnClickListener{
            val startNoteDetailIntent = Intent(this, NoteDetailsActivity::class.java)
            startActivity(startNoteDetailIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        noteAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(position: Int) {
        val note = NoteRepsoitory.listOfNotes[position]
        val updateNoteDetailIntent = Intent(this, NoteDetailsActivity::class.java)
        updateNoteDetailIntent.putExtra(INTENT_PARAMETER_EDIT_NOTE, note)
        updateNoteDetailIntent.putExtra(INTENT_PARAMETER_NOTE_POSITION, position)
        startActivityForResult(updateNoteDetailIntent, REQUEST_EDIT)
    }
}