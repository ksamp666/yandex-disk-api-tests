package framework.rest.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class SystemFoldersModel {
    var odnoklassniki: String? = null
    var google: String? = null
    var instagram: String? = null
    var vkontakte: String? = null
    var attach: String? = null
    var mailru: String? = null
    var downloads: String? = null
    var applications: String? = null
    var facebook: String? = null
    var social: String? = null
    var messenger: String? = null
    var calendar: String? = null
    var scans: String? = null
    var screenshots: String? = null
    var photostream: String? = null
}