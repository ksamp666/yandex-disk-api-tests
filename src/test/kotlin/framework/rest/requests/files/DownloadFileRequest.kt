package framework.rest.requests.files

import framework.rest.requests.RequestBase
import io.qameta.allure.Step
import io.restassured.response.ValidatableResponse
import java.lang.IllegalArgumentException

class DownloadFileRequest : RequestBase<DownloadFileRequest>() {

    private var downloadUrl: String? = null

    fun setDownloadUrl(value: String): DownloadFileRequest {
        downloadUrl = value
        return this
    }

    @Step("Sending DownloadFileRequest request")
    override fun sendRequest(): ValidatableResponse {
        if (downloadUrl == null) {
            throw IllegalArgumentException("Download URL should be set when using DownloadFileRequest")
        }
        useInvalidAuthToken()
        return getDefaultRequestSpecification()
            .urlEncodingEnabled(false)
            .get(downloadUrl)
            .then()
    }
}