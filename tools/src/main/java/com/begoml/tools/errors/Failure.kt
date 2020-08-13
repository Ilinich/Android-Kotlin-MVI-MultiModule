package com.begoml.tools.errors

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [PresentationFailure] class.
 */
//TODO We should use own Failure for each layer (domain, data, presentation)
sealed class Failure {

    /**
     * Extend this class for domain specific failures.
     */
    abstract class NetworkFailure : Failure() {
        object NetworkConnection : NetworkFailure()
        object EmptyNetworkResponse : NetworkFailure()
        object SocketTimeoutException : NetworkFailure()
        object UnknownHostFailure : NetworkFailure()
        class ServerError(val code: String) : Failure()
    }

    /**
     * Extend this class for presentation specific failures.
     */
    abstract class PresentationFailure(val exception: String) : Failure()

    /**
     * Extend this class for database specific failures.
     */
    abstract class DatabaseFailure : Failure() {
        object EmptyTable : DatabaseFailure()
        object SqlException : DatabaseFailure()
    }

    abstract class CacheFailure : Failure() {
        object DontNeedToUpdate : CacheFailure()
    }

    object None : Failure()

    data class UnknownFailure(val error: Exception) : Failure()
}