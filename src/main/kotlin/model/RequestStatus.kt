package model

import java.util.*

sealed class RequestStatus<T> {

    class Success<T>(val data: T) : RequestStatus<T>()

    class Loading<T>() : RequestStatus<T>()

    class Error<T>(val errorMessage: StringJoiner) : RequestStatus<T>()
}