package com.noor.yasser.ps.githubapp.utils


class ResultResponse<T> private constructor(val status: Status, val message: String?, val data: T) {
    enum class Status {
        SUCCESS, ERROR, LOADING, EMPTY
    }

    companion object {
        fun <T> success(data: T): ResultResponse<T> {
            return ResultResponse(Status.SUCCESS, null, data)
        }

        fun <T> success(data: T, message: String): ResultResponse<T> {
            return ResultResponse(Status.SUCCESS, message, data)
        }

        fun <T> error(msg: String, data: T): ResultResponse<T> {
            return ResultResponse(Status.ERROR, msg, data)
        }

        fun <T> loading(data: T): ResultResponse<T> {
            return ResultResponse(Status.LOADING, null, data)
        }

        fun <T> empty(data: T): ResultResponse<T> {
            return ResultResponse(Status.EMPTY, null, data)
        }
    }
}