package tests.api.functional

import com.shazam.shazamcrest.matcher.Matchers.sameBeanAs
import framework.rest.data.DefaultErrorData
import framework.rest.data.DiskDataData.Companion.getSkyainterviewUserDefaultState
import framework.rest.models.DefaultErrorModel
import framework.rest.models.DiskDataModel
import framework.rest.requests.DiskDataRequest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DiskDataTest {
    @DisplayName("Check positive disk data scenario")
    @Test
    fun positive() {
        val expectedDiskData = getSkyainterviewUserDefaultState()
        val actualDiskData = DiskDataRequest()
            .sendRequest()
            .statusCode(200)
            .extract()
            .`as`(DiskDataModel::class.java)

        assertThat(actualDiskData, sameBeanAs(expectedDiskData))
    }

    @DisplayName("Check scenario for not authorized user")
    @Test
    fun notAuthorized() {
        val expectedErrorObject = DefaultErrorData.getUnauthorizedError()
        val actualObject = DiskDataRequest()
            .useInvalidAuthToken()
            .sendRequest()
            .statusCode(401)
            .extract()
            .`as`(DefaultErrorModel::class.java)

        assertThat(actualObject, sameBeanAs(expectedErrorObject))
    }
}