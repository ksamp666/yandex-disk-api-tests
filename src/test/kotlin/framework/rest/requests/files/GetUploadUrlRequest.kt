package framework.rest.requests.files

import framework.rest.requests.RequestBase
import io.qameta.allure.Step
import io.restassured.response.ValidatableResponse

class GetUploadUrlRequest: RequestBase<GetUploadUrlRequest>() {
    companion object {
        private const val REQUEST_URL = "disk/resources/upload"
    }

    fun setPath(value: String): GetUploadUrlRequest {
        addRequestParam("path", value)
        return this
    }

    fun setOverwrite(value: Boolean): GetUploadUrlRequest {
        addRequestParam("overwrite", value.toString())
        return this
    }

    @Step("Sending GetUploadUrl request")
    override fun sendRequest(): ValidatableResponse {
        return getDefaultRequestSpecification()
            .get(url(REQUEST_URL))
            .then()
    }
}