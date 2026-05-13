package com.aetheralstudios.logger

data class LogConfig(
    val tag: String = "AppLogger",
    val minLevel: LogLevel = LogLevel.VERBOSE,
    val isEnabled: Boolean = true,
    val showThreadInfo: Boolean = false,
    val showCallerInfo: Boolean = false
)