package com.aetheralstudios.logger

import android.util.Log

internal class LogcatPrinter {

    fun print(level: LogLevel, tag: String, message: String, throwable: Throwable?) {
        val msg = if (throwable != null) "$message\n${Log.getStackTraceString(throwable)}" else message
        when (level) {
            LogLevel.VERBOSE -> Log.v(tag, msg)
            LogLevel.DEBUG   -> Log.d(tag, msg)
            LogLevel.INFO    -> Log.i(tag, msg)
            LogLevel.WARN    -> Log.w(tag, msg)
            LogLevel.ERROR   -> Log.e(tag, msg)
            LogLevel.ASSERT  -> Log.wtf(tag, msg)
        }
    }
}