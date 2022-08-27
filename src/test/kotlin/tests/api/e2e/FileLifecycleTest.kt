package tests.api.e2e

import framework.rest.models.MethodModel
import framework.rest.models.FilesListModel
import framework.rest.requests.files.*
import framework.utils.TestContentsManager
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FileLifecycleTest {
    companion object {
        private const val USER = "skyainterviewud"
        private const val LOCAL_FILE_PATH = "files/file_to_upload.txt"
        private const val YA_DISK_FILE_PATH = "some_folder/file_to_upload.txt"
    }

    @BeforeEach
    fun beforeTest() {
        DeleteFileOrFolderRequest()
            .useTokenForUser(USER)
            .setPath(YA_DISK_FILE_PATH)
            .setPermanently(true)
            .sendRequest()

        MatcherAssert.assertThat(getAllFilePaths(), not(hasItem(YA_DISK_FILE_PATH)))
    }

    private fun getAllFilePaths(): List<String> {
        return FilesListRequest()
            .useTokenForUser(USER)
            .sendRequest()
            .statusCode(200)
            .extract()
            .`as`(FilesListModel::class.java)
            .items
            .map { it.path?.substring(6) ?: "" }
    }

    @DisplayName("Check file lifecycle end to end scenario")
    @Test
    fun fileLifecycle() {
        val fileToUpload = TestContentsManager.getTestContentsFile(LOCAL_FILE_PATH)

        val uploadUrl = GetUploadUrlRequest()
            .useTokenForUser(USER)
            .setPath(YA_DISK_FILE_PATH)
            .setOverwrite(true)
            .sendRequest()
            .statusCode(200)
            .extract()
            .`as`(MethodModel::class.java)
            .href
        UploadFileRequest()
            .setUploadUrl(uploadUrl!!)
            .setRequestBody(fileToUpload)
            .sendRequest()
            .statusCode(201)
        MatcherAssert.assertThat(getAllFilePaths(), hasItem(YA_DISK_FILE_PATH))

        val downloadUrl = GetDownloadUrlRequest()
            .useTokenForUser(USER)
            .setPath(YA_DISK_FILE_PATH)
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
            fileToUpload.readBytes(), fileContents,
            "Downloaded file is different with uploaded"
        )
        MatcherAssert.assertThat(getAllFilePaths(), hasItem(YA_DISK_FILE_PATH))

        DeleteFileOrFolderRequest()
            .useTokenForUser(USER)
            .setPath(YA_DISK_FILE_PATH)
            .setPermanently(true)
            .sendRequest()
            .statusCode(204)
        MatcherAssert.assertThat(getAllFilePaths(), not(hasItem(YA_DISK_FILE_PATH)))
    }
}