package hr.fer.ruazosa.lectures.net.retrofit

import retrofit.ResponseCallback
import retrofit.RetrofitError
import retrofit.client.Response

class Response: ResponseCallback() {
    private var success: String? = null
    private var message: String? = null
    private var id: Int? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()

    fun getSuccess(): String? {
        return success
    }

    fun setSuccess(success: String?) {
        this.success = success
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getUserid(): Int? {
        return id
    }

    fun setUserid(id: Int?) {
        this.id = id
    }

    fun getAdditionalProperties(): Map<String, Any>? {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }

    override fun success(response: Response?) {
        print("Operation successed: " + success)
    }

    override fun failure(error: RetrofitError?) {
        print("Operation not success " + message)
    }
}