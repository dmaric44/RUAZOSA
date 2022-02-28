package hr.fer.ruazosa.noteapplication


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IOnItemClick {

    lateinit var notesAdapter: NotesAdapter
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listOfNotesAsRecyclerView = listOfNotesView as RecyclerView

        listOfNotesAsRecyclerView.layoutManager = LinearLayoutManager(applicationContext)

        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.cell_divider)!!)
        listOfNotesAsRecyclerView.addItemDecoration(decorator)


        viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                NotesViewModel::class.java)

        viewModel.initializeRepository(application)

        notesAdapter = NotesAdapter(viewModel, this)
        listOfNotesAsRecyclerView.adapter = notesAdapter

        viewModel.allNotes?.observe(this, Observer {
            notesAdapter.notifyDataSetChanged()
        })

        newNoteActionButton.setOnClickListener {
            val intent = Intent(this, NotesDetails::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, NotesDetails::class.java)
        val note = viewModel.allNotes.value?.get(position)
        intent.putExtra("note", note)
        startActivity(intent)
    }


}
