package hr.fer.tel.ruazosa.lectures.net.retrofit

import hr.fer.ruazosa.lectures.net.retrofit.Response
import hr.fer.tel.ruazosa.lectures.entity.Course
import hr.fer.tel.ruazosa.lectures.entity.ShortCourse
import hr.fer.tel.ruazosa.lectures.entity.ShortPerson
import retrofit.http.*

interface LecturesService {
    @get:GET("/courses")
    val listOfCourses: List<ShortCourse>

    @GET("/courses/{id}")
    fun getCourse(@Path("id") id: Long?): Course

    @GET("/courses/{id}/students")
    fun getCourseStudents(@Path("id") courseId: Long?): List<ShortPerson>

    @POST("/courses/{courseId}/students")
    fun enrollPersonToCourse(@Body person: ShortPerson?, @Path("courseId") courseId: Long?, callback: Response)

    @DELETE("/courses/{courseId}/students/{studentId}")
    fun disenrollPersonFromCourse(@Path("courseId") courseId: Long?, @Path("studentId") studentId: Long?, callback: Response)
}
