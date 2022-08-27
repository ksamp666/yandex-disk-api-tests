package framework.rest.requests.files

import framework.rest.requests.RequestBase
import io.qameta.allure.Step
import io.restassured.response.ValidatableResponse
import java.lang.IllegalArgumentException

class UploadFileRequest : RequestBase<UploadFileRequest>() {

    private var uploadUrl: String? = null

    fun setUploadUrl(value: String): UploadFileRequest {
        uploadUrl = value
        return this
    }

    @Step("Sending UploadFile request")
    override fun sendRequest(): ValidatableResponse {
        if (uploadUrl == null) {
            throw IllegalArgumentException("Upload URL should be set when using UploadFileRequest")
        }
        useInvalidAuthToken()
        return getDefaultRequestSpecification()
            .put(uploadUrl)
            .then()
    }
}