package framework.rest.steps

import framework.rest.models.DiskDataModel
import framework.rest.models.FilesListModel
import framework.rest.models.MethodModel
import framework.rest.requests.DiskDataRequest
import framework.rest.requests.files.*
import framework.utils.PropsReader
import io.qameta.allure.Step
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions
import java.io.File

class FileSteps(private var user: String = PropsReader.getDefaultUser()) {

    fun setUser(value: String): FileSteps {
        user = value
        return this
    }

    @Step("Remove file from disk")
    fun removeFile(filePath: String, shouldCheckRemovalStatusCode: Boolean = true): FileSteps {
        val resp = DeleteFileOrFolderRequest()
            .useTokenForUser(user)
            .setPath(filePath)
            .setPermanently(true)
            .sendRequest()

        if (shouldCheckRemovalStatusCode) {
            resp.statusCode(204)
        }

        return this
    }

    @Step("Check file is absent on disk")
    fun checkFileIsAbsentOnDisk(filePath: String): FileSteps {
        MatcherAssert.assertThat(
            getAllFilePaths(),
            CoreMatchers.not(CoreMatchers.hasItem(filePath))
        )

        return this
    }

    @Step("Upload file to disk")
    fun uploadFile(localFileToUpload: File, yaDiskFilePath: String): FileSteps {
        val uploadUrl = GetUploadUrlRequest()
            .useTokenForUser(user)
            .setPath(yaDiskFilePath)
            .setOverwrite(true)
            .sendRequest()
            .statusCode(200)
            .extract()
            .`as`(MethodModel::class.java)
            .href
        UploadFileRequest()
            .setUploadUrl(uploadUrl!!)
            .setRequestBody(localFileToUpload)
            .sendRequest()
            .statusCode(201)

        return this
    }

    @Step("Check file is present on disk")
    fun checkFileIsPresentOnDisk(yaDiskFilePath: String): FileSteps {
        MatcherAssert.assertThat(getAllFilePaths(), CoreMatchers.hasItem(yaDiskFilePath))
        return this
    }

    @Step("Download and check file")
    fun downloadAndCheckFile(yaDiskFilePath: String, expectedFile: File): FileSteps {
        val downloadUrl = GetDownloadUrlRequest()
            .useTokenForUser(user)
            .setPath(yaDiskFilePath)
            .sendRequest()
            .statusCode(200)
            .extract()
            .`as`(MethodModel::class.java)
            .href
        val fileContents = DownloadFileRequest()
            .setDownloadUrl(downloadUrl!!)
            .sendRequest()
            .statusCode(200)
            .extract()
            .asByteArray()
        Assertions.assertArrayEquals(
            expectedFile.readBytes(), fileContents,
            "Downloaded file is different with reference file"
        )

        return this
    }

    @Step("Check used space is increased")
    fun checkUsedSpaceIsIncreased(compareToValue: Long): FileSteps {
        MatcherAssert.assertThat(
            "It is expected that used space value is increased",
            getUsedSpace(), Matchers.greaterThan(compareToValue)
        )
        return this
    }

    @Step("Check used space is the same")
    fun checkUsedSpaceIsTheSame(compareToValue: Long): FileSteps {
        Assertions.assertEquals(
            compareToValue, getUsedSpace(),
            "It is expected that used space value is the same"
        )
        return this
    }

    @Step("Get used space on disk")
    fun getUsedSpace(): Long {
        return DiskDataRequest()
            .useTokenForUser(user)
            .sendRequest()
            .extract()
            .`as`(DiskDataModel::class.java)
            .usedSpace!!
    }

    private fun getAllFilePaths(): List<String> {
        return FilesListRequest()
            .useTokenForUser(user)
            .sendRequest()
            .statusCode(200)
            .extract()
            .`as`(FilesListModel::class.java)
            .items
            .map { it.path?.substring(6) ?: "" }
    }
}