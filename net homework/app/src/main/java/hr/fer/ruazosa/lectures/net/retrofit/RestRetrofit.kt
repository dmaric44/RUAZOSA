package hr.fer.tel.ruazosa.lectures.net.retrofit

import hr.fer.ruazosa.lectures.net.retrofit.CreatePersonResponse
import hr.fer.ruazosa.lectures.net.retrofit.DeletePersonResponse
import hr.fer.ruazosa.lectures.net.retrofit.PersonsService
import hr.fer.ruazosa.lectures.net.retrofit.Response

import java.io.IOException

import hr.fer.tel.ruazosa.lectures.entity.Course
import hr.fer.tel.ruazosa.lectures.entity.Person
import hr.fer.tel.ruazosa.lectures.entity.ShortCourse
import hr.fer.tel.ruazosa.lectures.entity.ShortPerson
import hr.fer.tel.ruazosa.lectures.net.RestFactory
import hr.fer.tel.ruazosa.lectures.net.RestInterface
import retrofit.Callback
import retrofit.ResponseCallback
import retrofit.RestAdapter

class RestRetrofit : RestInterface {
    private val service: LecturesService
    private val personsService: PersonsService

    init {
        val baseURL = "http://" + RestFactory.BASE_IP + ":8080/api/"
        val retrofit = RestAdapter.Builder()
                .setEndpoint(baseURL)
                .build()

        service = retrofit.create(LecturesService::class.java)
        personsService = retrofit.create(PersonsService::class.java)
    }

    override fun getListOfCourses(): List<ShortCourse>? {
        return service.listOfCourses
    }

    override fun getCourse(id: Long?): Course? {
        return service.getCourse(id)
    }

    override fun getCourseStudents(courseId: Long?): List<ShortPerson>? {
        return service.getCourseStudents(courseId)
    }

    override fun getListOfPersons(): List<ShortPerson>? {
        return personsService.listOfPersons
    }

    override fun getPerson(id: Long?): Person? {
        return personsService.getPerson(id)
    }

    override fun createPerson(person: Person?) {
        val callback = CreatePersonResponse()
        personsService.createPerson(person, callback)
    }

    override fun deletePerson(id: Long?) {
        val callback = DeletePersonResponse()
        personsService.deletePerson(id, callback)
//        println(callback.getMessage() +" " + callback.getSuccess())
    }

    override fun enrollPersonToCourse(person: ShortPerson?, courseId: Long?): Boolean? {
        val callback = Response()
        service.enrollPersonToCourse(person, courseId, callback)
        return true
    }

    override fun disenrollPersonFromCourse(personId: Long?, courseId: Long?): Boolean? {
        val callback = Response()
        service.disenrollPersonFromCourse(courseId, personId, callback)
        return true
    }

    override fun getListOfStudentsOnCourse(id: Long?): List<ShortPerson>? {
        return service.getCourseStudents(id)
    }
}
