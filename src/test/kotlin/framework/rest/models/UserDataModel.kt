package framework.rest.models

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class UserDataModel {
    var country: String? = null
    var login: String? = null

    @JsonAlias("display_name")
    var displayName: String? = null
    var uid: Long? = null
}