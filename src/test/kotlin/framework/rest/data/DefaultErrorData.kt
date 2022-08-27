package framework.rest.data

import framework.rest.models.DefaultErrorModel

class DefaultErrorData {
    companion object {
        fun getUnauthorizedError(): DefaultErrorModel {
            val error = DefaultErrorModel()
            error.message = "Не авторизован."
            error.description = "Unauthorized"
            error.error = "UnauthorizedError"
            return error
        }
    }
}