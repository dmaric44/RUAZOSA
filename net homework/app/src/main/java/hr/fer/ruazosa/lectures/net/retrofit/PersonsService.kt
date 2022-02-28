package hr.fer.ruazosa.lectures.net.retrofit

import com.squareup.okhttp.Call
import com.squareup.okhttp.Response
import hr.fer.tel.ruazosa.lectures.entity.Person
import hr.fer.tel.ruazosa.lectures.entity.ShortPerson
import retrofit.ResponseCallback
import retrofit.RestAdapter
import retrofit.Callback
import retrofit.http.*

interface PersonsService {
    @get:GET("/persons")
    val listOfPersons: List<ShortPerson>

    @GET("/persons/{id}")
    fun getPerson(@Path("id") id: Long?): Person

    @POST("/persons")
    fun createPerson(@Body person: Person?, callback: CreatePersonResponse)

    @DELETE("/persons/{id}")
    fun deletePerson(@Path("id") id: Long?, callback: DeletePersonResponse)
}