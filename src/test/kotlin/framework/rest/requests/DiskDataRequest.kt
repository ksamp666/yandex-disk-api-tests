package framework.rest.requests

import io.qameta.allure.Step
import io.restassured.response.ValidatableResponse

class DiskDataRequest : RequestBase<DiskDataRequest>() {
    companion object {
        private const val REQUEST_URL = "disk"
    }

    @Step("Sending DiskData request")
    override fun sendRequest(): ValidatableResponse {
        return getDefaultRequestSpecification()
            .get(url(REQUEST_URL))
            .then()
    }
}