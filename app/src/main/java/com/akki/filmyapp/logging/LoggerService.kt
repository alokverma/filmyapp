package com.akki.filmyapp.logging

import timber.log.Timber

class LoggerService : ILogger {

    override fun logMessage(tag: String?, message: String) {
        Timber.tag(tag!!).d(message)
    }

    override fun logException(exception: Exception) {
        Timber.d(exception)
    }

}