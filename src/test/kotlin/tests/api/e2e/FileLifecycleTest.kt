package tests.api.e2e

import framework.rest.steps.FileSteps
import framework.utils.TestContentsManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FileLifecycleTest {
    companion object {
        private const val USER = "skyainterviewud"
        private const val LOCAL_FILE_PATH = "files/file_to_upload.txt"
        private const val YA_DISK_FILE_PATH = "some_folder/file_to_upload.txt"
    }

    private val fileSteps = FileSteps(USER)

    @BeforeEach
    fun beforeTest() {
        fileSteps
            .removeFile(YA_DISK_FILE_PATH, false)
            .checkFileIsAbsentOnDisk(YA_DISK_FILE_PATH)
    }

    @DisplayName("Check file lifecycle end to end scenario")
    @Test
    fun fileLifecycle() {
        val fileToUpload = TestContentsManager.getTestContentsFile(LOCAL_FILE_PATH)

        val usedSpace = fileSteps.getUsedSpace()
        fileSteps
            .uploadFile(fileToUpload, YA_DISK_FILE_PATH)
            .checkFileIsPresentOnDisk(YA_DISK_FILE_PATH)
            .checkUsedSpaceIsIncreased(usedSpace)
            .downloadAndCheckFile(YA_DISK_FILE_PATH, fileToUpload)
            .checkFileIsPresentOnDisk(YA_DISK_FILE_PATH)
            .removeFile(YA_DISK_FILE_PATH, true)
            .checkFileIsAbsentOnDisk(YA_DISK_FILE_PATH)
            .checkUsedSpaceIsTheSame(usedSpace)
    }
}