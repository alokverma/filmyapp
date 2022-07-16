package com.akki.filmyapp.logging

import java.lang.Exception

interface ILogger {
    fun logMessage(message:String)
    fun logException(exception: Exception)
}