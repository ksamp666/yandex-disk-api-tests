package framework.rest.requests.files

import framework.rest.requests.RequestBase
import io.qameta.allure.Step
import io.restassured.response.ValidatableResponse

class DeleteFileOrFolderRequest: RequestBase<DeleteFileOrFolderRequest>() {
    companion object {
        private const val REQUEST_URL = "disk/resources"
    }

    fun setPath(value: String): DeleteFileOrFolderRequest {
        addRequestParam("path", value)
        return this
    }

    fun setPermanently(value: Boolean): DeleteFileOrFolderRequest {
        addRequestParam("permanently", value.toString())
        return this
    }

    @Step("Sending DeleteFileOrFolder request")
    override fun sendRequest(): ValidatableResponse {
        return getDefaultRequestSpecification()
            .delete(url(REQUEST_URL))
            .then()
    }
}