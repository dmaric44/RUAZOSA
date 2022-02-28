package hr.fer.ruazosa.lectures

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import hr.fer.tel.ruazosa.lectures.entity.ShortPerson
import hr.fer.tel.ruazosa.lectures.net.RestFactory
import kotlinx.android.synthetic.main.activity_delete_student_from_course.*

/**
 * Pogledati napomenu u AddStudentToCourse.kt
 * Nije moguće provjeriti brisanje ako se ne može napraviti POST zahtjev
 */

class DeleteStudentFromCourse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_student_from_course)

        val courseId = intent.getLongExtra("courseId", -1)
        LoadStudentsTask().execute(courseId)

        deleteStudentsListView?.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val rest = RestFactory.instance
            val itemAtPosition = parent.getItemAtPosition(position)
            val shortPerson = itemAtPosition as ShortPerson
            rest.disenrollPersonFromCourse(shortPerson.id, courseId)
            finish()
        }

    }



    private inner class LoadStudentsTask: AsyncTask<Long, Void, List<ShortPerson>?>(){
        override fun doInBackground(vararg courseId: Long?): List<ShortPerson>? {
            val rest = RestFactory.instance
            var studentsOnCourse = rest.getListOfStudentsOnCourse(courseId[0])
            return studentsOnCourse
        }
        override fun onPostExecute(result: List<ShortPerson>?) {
            super.onPostExecute(result)
            updateStudentsList(result)
        }
    }

    private fun updateStudentsList(students: List<ShortPerson>?) {
        if(students != null){
            val adapter = StudentsAdapter(this, android.R.layout.simple_list_item_1, students)
            deleteStudentsListView.adapter = adapter
        }
    }
    private inner class StudentsAdapter(context: Context, textViewResourceId: Int, private val shortStudentsList: List<ShortPerson>) : ArrayAdapter<ShortPerson>(context, textViewResourceId, shortStudentsList)

}
