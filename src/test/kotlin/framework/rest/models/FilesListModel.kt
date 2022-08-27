package framework.rest.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class FilesListModel {
    var items: List<FileDataModel> = ArrayList()
    var limit: Int? = null
    var offset: Int? = null
}