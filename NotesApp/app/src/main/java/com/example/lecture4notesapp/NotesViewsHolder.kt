package com.example.lecture4notesapp

import android.content.Intent
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NotesViewsHolder(v: View, listener:IOnItemClick, adapter: NotesAdapter): RecyclerView.ViewHolder(v), View.OnClickListener {
    val notesAdapter = adapter
    var listener: IOnItemClick? = null
    var noteTitleTextView: TextView? = null
    var noteDateTextView: TextView? = null
    var deleteNoteButton: ImageButton? = null

    init {
        noteTitleTextView = v.findViewById(R.id.titleTextViewId)
        noteDateTextView = v.findViewById(R.id.dateTextViewId)
        deleteNoteButton = v.findViewById(R.id.deleteNoteButtonId)
        this.listener = listener

        deleteNoteButton?.setOnClickListener{
            NoteRepsoitory.listOfNotes.removeAt(adapterPosition)
            notesAdapter.notifyDataSetChanged()
        }

        v.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        listener?.onItemClick(adapterPosition)
    }
}