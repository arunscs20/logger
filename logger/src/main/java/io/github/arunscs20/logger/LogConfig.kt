package io.github.arunscs20.logger

/**
 * Configuration settings for the logging system.
 *
 * This class defines how logs are filtered, formatted, and whether they
 * are displayed based on the build environment.
 *
 * @property tag The default identification label for logs. Default is "Logger".
 * @property minLevel The minimum [LogLevel] required for a log to be processed.
 * Defaults to [LogLevel.VERBOSE].
 * @property isEnabled Globally enables or disables all logging operations.
 * @property debugOnly If true, logs will be suppressed in release builds even if [isEnabled] is true.
 * @property showThreadInfo When true, includes the name of the calling thread in the log output.
 * @property showCallerInfo When true, includes the file name and line number of the log caller.
 */
data class LogConfig(
    var tag: String = "Logger",
    var minLevel: LogLevel = LogLevel.VERBOSE,
    var isEnabled: Boolean = true,
    var debugOnly: Boolean = true,
    var showThreadInfo: Boolean = false,
    var showCallerInfo: Boolean = false
)