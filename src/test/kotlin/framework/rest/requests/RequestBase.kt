package framework.rest.requests

import framework.utils.PropsReader
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured.given
import io.restassured.specification.RequestSpecification
import java.io.File


abstract class RequestBase<RequestType> : IRequest {
    companion object {
        private val DEFAULT_USER = PropsReader.getDefaultUser()
        private val BASE_URL = PropsReader.getApiUrl()
        private val DEFAULT_TOKEN = PropsReader.getApiOauthTokenForUser(DEFAULT_USER)
    }

    private var authToken = DEFAULT_TOKEN
    private val params = HashMap<String, String>()
    private var body: Any? = null

    protected fun url(path: String): String {
        return BASE_URL + path
    }

    protected fun getDefaultRequestSpecification(): RequestSpecification {
        val reqSpec = given()
            .filter(AllureRestAssured())
            .header("Authorization", "OAuth $authToken")
            .params(params)

        if (body != null) {
            if (body is File) {
                reqSpec.body(body as File)
            } else {
                reqSpec.body(body)
            }
        }

        return reqSpec
    }

    fun addRequestParam(key: String, value: String): RequestType {
        params[key] = value
        return this as RequestType
    }

    fun useInvalidAuthToken(): RequestType {
        authToken = "invalid_token"
        return this as RequestType
    }

    fun useTokenForUser(userName: String): RequestType {
        authToken = PropsReader.getApiOauthTokenForUser(userName)
        return this as RequestType
    }

    fun setRequestBody(value: Any): RequestType {
        body = value
        return this as RequestType
    }
}