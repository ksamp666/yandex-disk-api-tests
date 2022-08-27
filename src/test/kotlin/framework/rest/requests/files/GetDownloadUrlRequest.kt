package framework.rest.requests.files

import framework.rest.requests.RequestBase
import io.qameta.allure.Step
import io.restassured.response.ValidatableResponse

class GetDownloadUrlRequest: RequestBase<GetDownloadUrlRequest>() {
    companion object {
        private const val REQUEST_URL = "disk/resources/download"
    }

    fun setPath(value: String): GetDownloadUrlRequest {
        addRequestParam("path", value)
        return this
    }

    @Step("Sending GetDownloadUrl request")
    override fun sendRequest(): ValidatableResponse {
        return getDefaultRequestSpecification()
            .get(url(REQUEST_URL))
            .then()
    }
}