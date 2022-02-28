package hr.fer.ruazosa.lectures

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import hr.fer.tel.ruazosa.lectures.entity.ShortCourse
import hr.fer.tel.ruazosa.lectures.entity.ShortPerson
import hr.fer.tel.ruazosa.lectures.net.RestFactory
import kotlinx.android.synthetic.main.activity_add_student_to_course.*

/**
 * Napomena: pri izvršavanju aplikacije u kombinaciji sa danim poslužiteljem ne događa se ništa.
 * Pomoću CURL alata kada se pošalje zahtjev "curl -v -X OPTIONS http://localhost:8080/api/courses/1/students"
 * na popisu dozvoljenih metoda nije metoda POST tako da nije moguće isprobati radi li ispravno napisani kod
 */


class AddStudentToCourse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student_to_course)

        val courseId = intent.getLongExtra("courseId", -1)
        LoadStudentsTask().execute(courseId)

        studentsListView?.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val rest = RestFactory.instance
            val itemAtPosition = parent.getItemAtPosition(position)
            val shortPerson = itemAtPosition as ShortPerson
            rest.enrollPersonToCourse(shortPerson, courseId)
            finish()
        }

    }



    private inner class LoadStudentsTask: AsyncTask<Long, Void, List<ShortPerson>?>(){
        override fun doInBackground(vararg courseId: Long?): List<ShortPerson>? {
            val rest = RestFactory.instance
            var allStudents = rest.getListOfPersons()
            var studentsOnCourse = rest.getListOfStudentsOnCourse(courseId[0])
            var studentsNotOnCourse: MutableList<ShortPerson> = mutableListOf<ShortPerson>()

            if (studentsOnCourse != null && allStudents != null) {
                for(person in allStudents){
                    var isOnCourse = false
                    for(student in studentsOnCourse){
                        if(person.id == student.id)
                            isOnCourse = true
                    }
                    if(!isOnCourse){
                        studentsNotOnCourse.add(person)
                    }
                }
            }
            return studentsNotOnCourse
        }
        override fun onPostExecute(result: List<ShortPerson>?) {
            super.onPostExecute(result)
            updateStudentsList(result)
        }
    }

    private fun updateStudentsList(students: List<ShortPerson>?) {
        if(students != null){
            val adapter = StudentsAdapter(this, android.R.layout.simple_list_item_1, students)
            studentsListView.adapter = adapter
        }
    }
    private inner class StudentsAdapter(context: Context, textViewResourceId: Int, private val shortStudentsList: List<ShortPerson>) : ArrayAdapter<ShortPerson>(context, textViewResourceId, shortStudentsList)

}