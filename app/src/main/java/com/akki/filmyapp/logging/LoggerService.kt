package com.akki.filmyapp.logging

import timber.log.Timber

class LoggerService : ILogger {

    override fun logMessage(message: String) {
        Timber.tag("LoggerService").d(message)
    }

    override fun logException(exception: Exception) {
        Timber.d(exception)
    }

}