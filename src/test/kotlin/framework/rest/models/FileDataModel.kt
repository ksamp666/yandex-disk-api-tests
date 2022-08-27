package framework.rest.models

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class FileDataModel {
    var size: Long? = null
    var name: String? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    var created: Date? = null

    @JsonAlias("mime_type")
    var mimeType: String? = null
    var path: String? = null

    @JsonAlias("media_type")
    var mediaType: String? = null
}