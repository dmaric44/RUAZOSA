package hr.fer.ruazosa.noteapplication
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView



class NotesAdapter(listOfNotesViewModel: NotesViewModel, listener: IOnItemClick): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var listOfNotes: NotesViewModel = listOfNotesViewModel
    var notesViewModel = listOfNotesViewModel
    var mainListener = listener

    class ViewHolder(itemView: View, notesModel: NotesViewModel, listener: IOnItemClick): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var noteTitleTextView: TextView? = null
        var noteDateTextView: TextView? = null
        var deleteNoteButton: ImageButton? = null
        var listener: IOnItemClick? = null

        init {
            noteTitleTextView = itemView.findViewById(R.id.noteTitleTextView)
            noteDateTextView = itemView.findViewById(R.id.noteDateTextView)
            deleteNoteButton = itemView.findViewById(R.id.deleteNoteButton)

            deleteNoteButton?.setOnClickListener {
                notesModel.deleteNote(adapterPosition)
            }
            this.listener = listener
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener?.onItemClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NotesAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val notesListElement = inflater.inflate(R.layout.note_list_element, parent, false)
        return ViewHolder(notesListElement, notesViewModel, mainListener)
    }

    override fun getItemCount(): Int {
        if (listOfNotes.allNotes.value != null) {
            return listOfNotes.allNotes.value!!.count()
        }
        return 0
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (listOfNotes != null) {
            viewHolder.noteTitleTextView?.text = listOfNotes.allNotes?.value!![position].noteTitle


            viewHolder.noteDateTextView?.text =
                listOfNotes.allNotes?.value!![position].noteDate.toString()
        }
    }

}