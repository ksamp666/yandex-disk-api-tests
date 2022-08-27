package framework.rest.data

import framework.rest.models.DiskDataModel
import framework.rest.models.SystemFoldersModel
import framework.rest.models.UserDataModel

class DiskDataData {
    companion object {
        fun getSkyainterviewUserDefaultState(): DiskDataModel {
            val user = UserDataModel()
            user.country = "ru"
            user.login = "skyainterview"
            user.displayName = "skyainterview"
            user.uid = 1679536520

            val systemFolders = SystemFoldersModel()
            systemFolders.odnoklassniki = "disk:/Социальные сети/Одноклассники"
            systemFolders.google = "disk:/Социальные сети/Google+"
            systemFolders.instagram = "disk:/Социальные сети/Instagram"
            systemFolders.vkontakte = "disk:/Социальные сети/ВКонтакте"
            systemFolders.attach = "disk:/Почтовые вложения"
            systemFolders.mailru = "disk:/Социальные сети/Мой Мир"
            systemFolders.downloads = "disk:/Загрузки/"
            systemFolders.applications = "disk:/Приложения"
            systemFolders.facebook = "disk:/Социальные сети/Facebook"
            systemFolders.social = "disk:/Социальные сети/"
            systemFolders.messenger = "disk:/Файлы Мессенджера"
            systemFolders.calendar = "disk:/Материалы встреч"
            systemFolders.scans = "disk:/Сканы"
            systemFolders.screenshots = "disk:/Скриншоты/"
            systemFolders.photostream = "disk:/Фотокамера/"

            val diskData = DiskDataModel()
            diskData.maxFileSize = 1073741824
            diskData.paidMaxFileSize = 53687091200
            diskData.totalSpace = 5368709120
            diskData.trashSize = 0
            diskData.paid = false
            diskData.usedSpace = 40821195
            diskData.unlimitedAutoUploadEnabled = false
            diskData.user = user
            diskData.systemFolders = systemFolders

            return diskData
        }
    }
}