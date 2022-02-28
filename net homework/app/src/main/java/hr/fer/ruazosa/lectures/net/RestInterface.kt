package hr.fer.tel.ruazosa.lectures.net

import hr.fer.tel.ruazosa.lectures.entity.Course
import hr.fer.tel.ruazosa.lectures.entity.Person
import hr.fer.tel.ruazosa.lectures.entity.ShortCourse
import hr.fer.tel.ruazosa.lectures.entity.ShortPerson

interface RestInterface {
    fun getListOfCourses(): List<ShortCourse>?
    fun getListOfPersons(): List<ShortPerson>?

    fun getCourse(id: Long?): Course?
    fun getCourseStudents(courseId: Long?): List<ShortPerson>?
    fun getPerson(id: Long?): Person?

    fun createPerson(person: Person?)
    fun deletePerson(id: Long?)

    fun enrollPersonToCourse(person: ShortPerson?, courseId: Long?): Boolean?
    fun disenrollPersonFromCourse(personId: Long?, courseId: Long?): Boolean?
    fun getListOfStudentsOnCourse(id: Long?): List<ShortPerson>?
}
