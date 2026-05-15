package io.github.arunscs20.logger

import android.util.Log

internal class LogcatPrinter {

    /**
     * Internal core method that performs the actual system log call.
     *
     * This method formats the final message (appending stack traces if a [throwable]
     * is present) and maps the internal [LogLevel] to the corresponding
     * [android.util.Log] priority level.
     *
     * @param level The severity level used to determine which Android Log method to call.
     * @param tag The identification label for the log.
     * @param message The primary text message to be logged.
     * @param throwable An optional exception. If provided, its stack trace is appended to the message.
     */
    fun print(level: LogLevel, tag: String, message: String, throwable: Throwable?) {
        val msg =
            if (throwable != null) "$message\n${Log.getStackTraceString(throwable)}" else message
        when (level) {
            LogLevel.VERBOSE -> Log.v(tag, msg)
            LogLevel.DEBUG -> Log.d(tag, msg)
            LogLevel.INFO -> Log.i(tag, msg)
            LogLevel.WARN -> Log.w(tag, msg)
            LogLevel.ERROR -> Log.e(tag, msg)
            LogLevel.ASSERT -> Log.wtf(tag, msg)
        }
    }
}