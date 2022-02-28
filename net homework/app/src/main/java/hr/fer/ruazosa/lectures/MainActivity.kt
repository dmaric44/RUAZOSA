package hr.fer.ruazosa.lectures

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import hr.fer.tel.ruazosa.lectures.entity.ShortCourse
import hr.fer.tel.ruazosa.lectures.net.RestFactory
import kotlinx.android.synthetic.main.activity_main.*
import hr.fer.tel.ruazosa.lectures.entity.ShortPerson

class MainActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var loadCourseButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView) as ListView
        listView?.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val itemAtPosition = parent.getItemAtPosition(position)
            val shortCourse = itemAtPosition as ShortCourse
            val intent = Intent(this@MainActivity, CourseDetailsActivity::class.java)
            intent.putExtra("course", shortCourse)
            startActivity(intent)
        }

        listViewPersons?.onItemClickListener = AdapterView.OnItemClickListener {parent, _, position, _ ->
            val itemAtPosition = parent.getItemAtPosition(position)
            val shortPerson = itemAtPosition as ShortPerson

            val intent = Intent(this, EditDeletePerson::class.java)
            intent.putExtra("id", shortPerson.id)
            startActivity(intent)
        }

        loadCourseButton = findViewById(R.id.loadCourseButton) as Button
        loadCourseButton?.setOnClickListener {
            LoadCoursesTask().execute()
        }

        loadPersonsButton.setOnClickListener {
            LoadStudentsTask().execute()
        }

        createPersonButton.setOnClickListener {
            val intent = Intent(this, CreatePersonActivity::class.java)
            startActivity(intent)
        }


    }


    private inner class LoadCoursesTask: AsyncTask<Void, Void, List<ShortCourse>?>() {
        override fun doInBackground(vararg params: Void): List<ShortCourse>? {
            val rest = RestFactory.instance
            return rest.getListOfCourses()
        }

        override fun onPostExecute(courses: List<ShortCourse>?) {
            updateCourseList(courses)
        }
    }

    private inner class LoadStudentsTask:AsyncTask<Void, Void, List<ShortPerson>?>() {
        override fun doInBackground(vararg p0: Void?): List<ShortPerson>? {
            val rest = RestFactory.instance
            return rest.getListOfPersons()
        }

        override fun onPostExecute(persons: List<ShortPerson>?) {
            updatePersonsList(persons)
        }
    }

    private fun updatePersonsList(persons: List<ShortPerson>?){
        if(persons != null){
            val adapter = PersonAdapter(this, android.R.layout.simple_list_item_1, persons)
            listViewPersons.adapter = adapter
        }
    }

    private fun updateCourseList(courses: List<ShortCourse>?) {
        if(courses != null) {
            val adapter = CourseAdapter(this,
                android.R.layout.simple_list_item_1, courses)
            listView?.adapter = adapter
            adapter.notifyDataSetChanged()
        } else {
            // TODO show that courses can not be loaded
        }
    }

    private inner class CourseAdapter(context: Context, textViewResourceId: Int, private val shortCourseList: List<ShortCourse>) : ArrayAdapter<ShortCourse>(context, textViewResourceId, shortCourseList)
    private inner class PersonAdapter(context: Context, textViewResourceId: Int, private val shortPersonsList: List<ShortPerson>) : ArrayAdapter<ShortPerson>(context, textViewResourceId, shortPersonsList)
}
