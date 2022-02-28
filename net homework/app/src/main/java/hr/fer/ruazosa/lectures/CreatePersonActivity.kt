package hr.fer.ruazosa.lectures

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import hr.fer.tel.ruazosa.lectures.entity.Person
import hr.fer.tel.ruazosa.lectures.net.RestFactory
import kotlinx.android.synthetic.main.activity_create_person.*

class CreatePersonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_person)

        createNewPersonButton.setOnClickListener {
            val person = Person()
            person.id = null
            person.firstName = firstNameEditText.text.toString()
            person.lastName = lastNameEditText.text.toString()
            person.phone = phoneEditText.text.toString()
            person.room = roomEditText.text.toString()

            val rest = RestFactory.instance
            rest.createPerson(person)
            finish()
        }
    }
}