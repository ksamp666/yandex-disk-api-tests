package framework.rest.models

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class DiskDataModel {
    @JsonAlias("max_file_size")
    var maxFileSize: Long? = null

    @JsonAlias("paid_max_file_size")
    var paidMaxFileSize: Long? = null

    @JsonAlias("total_space")
    var totalSpace: Long? = null

    @JsonAlias("trash_size")
    var trashSize: Long? = null

    @JsonAlias("is_paid")
    var paid: Boolean? = null

    @JsonAlias("used_space")
    var usedSpace: Long? = null

    @JsonAlias("system_folders")
    var systemFolders: SystemFoldersModel? = null

    var user: UserDataModel? = null

    @JsonAlias("unlimited_autoupload_enabled")
    var unlimitedAutoUploadEnabled: Boolean? = null
}