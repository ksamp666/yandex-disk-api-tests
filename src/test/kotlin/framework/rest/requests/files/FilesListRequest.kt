package framework.rest.requests.files

import framework.rest.requests.RequestBase
import io.qameta.allure.Step
import io.restassured.response.ValidatableResponse

class FilesListRequest: RequestBase<FilesListRequest>() {
    companion object {
        private const val REQUEST_URL = "disk/resources/files"
    }

    @Step("Sending FilesList request")
    override fun sendRequest(): ValidatableResponse {
        return getDefaultRequestSpecification()
            .get(url(REQUEST_URL))
            .then()
    }
}