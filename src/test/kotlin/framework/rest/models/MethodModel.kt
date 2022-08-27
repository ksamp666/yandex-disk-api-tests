package framework.rest.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class MethodModel {
    var href: String? = null
    var method: String? = null
    var templated: Boolean? = null
}