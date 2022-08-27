package framework.utils

import java.io.File

class TestContentsManager {
    companion object {
        private const val TEST_CONTENTS_FOLDER_PATH = "src/test/resources/contents/"

        fun getTestContentsFile(path: String): File {
            return File(TEST_CONTENTS_FOLDER_PATH + path)
        }
    }
}