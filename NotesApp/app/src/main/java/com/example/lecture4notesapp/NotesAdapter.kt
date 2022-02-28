package com.example.lecture4notesapp

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class NotesAdapter(listener: IOnItemClick): RecyclerView.Adapter<NotesViewsHolder>() {
    val listener = listener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewsHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteListElement = inflater.inflate(R.layout.note_cell, parent, false)
        return NotesViewsHolder(noteListElement, listener, this)
    }

    override fun onBindViewHolder(holder: NotesViewsHolder, position: Int) {
        holder.noteDateTextView?.text = NoteRepsoitory.listOfNotes[position].noteDate.toString()
        holder.noteTitleTextView?.text = NoteRepsoitory.listOfNotes[position].noteTitle
    }

    override fun getItemCount(): Int {
        return NoteRepsoitory.listOfNotes.size
    }


}