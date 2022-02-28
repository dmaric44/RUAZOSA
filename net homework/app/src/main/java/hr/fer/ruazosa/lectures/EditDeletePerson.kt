package hr.fer.ruazosa.lectures

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import hr.fer.tel.ruazosa.lectures.entity.Person
import hr.fer.tel.ruazosa.lectures.net.RestFactory
import kotlinx.android.synthetic.main.activity_edit_delete_person.*

class EditDeletePerson : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_delete_person)

        val personId = intent.getLongExtra("id", -1)
        LoadPersonTask().execute(personId)

        deletePersonButton.setOnClickListener {
            DeletePersonTask().execute(personId)
        }

    }

    private inner class DeletePersonTask:AsyncTask<Long, Void, Void>(){
        override fun doInBackground(vararg params: Long?): Void? {
            val personId = params[0]
            val rest = RestFactory.instance
            val allCourses = rest.getListOfCourses()
            if (allCourses != null) {
                for(course in allCourses){
                    rest.disenrollPersonFromCourse(personId, course.id)
                }
            }
            rest.deletePerson(personId)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            finish()
        }

    }

    private inner class LoadPersonTask: AsyncTask<Long, Void, Person?>(){
        override fun doInBackground(vararg params: Long?): Person? {
            val rest = RestFactory.instance
            return rest.getPerson(params[0])
        }

        override fun onPostExecute(result: Person?) {
            super.onPostExecute(result)
            updatePersonDetails(result)
        }
    }


    private fun updatePersonDetails(person: Person?){
        if(person != null){
            firstNameEditText.setText(person.firstName)
            lastNameEditText.setText(person.lastName)
            roomEditText.setText(person.room)
            phoneEditText.setText(person.phone)
        }
    }
}