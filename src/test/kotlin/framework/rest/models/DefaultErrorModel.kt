package framework.rest.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class DefaultErrorModel {
    var message: String? = null
    var description: String? = null
    var error: String? = null
}