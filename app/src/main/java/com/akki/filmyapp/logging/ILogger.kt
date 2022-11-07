package com.akki.filmyapp.logging

import java.lang.Exception

interface ILogger {
    fun logMessage(tag: String? = "MovieApp", message: String)
    fun logException(exception: Exception)
}
