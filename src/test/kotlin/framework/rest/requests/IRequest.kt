package framework.rest.requests

import io.restassured.response.ValidatableResponse

interface IRequest {
    fun sendRequest(): ValidatableResponse
}