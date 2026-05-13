package com.aetheralstudios.logger

object Logger {

    private var config = LogConfig()
    private val logcatPrinter = LogcatPrinter()

    /** Call once in Application.onCreate() */
    fun init(block: LogConfig.() -> Unit = {}) {
        config = LogConfig().apply(block)
    }

    fun v(message: String, tag: String? = null, throwable: Throwable? = null) =
        log(LogLevel.VERBOSE, tag, message, throwable)

    fun d(message: String, tag: String? = null, throwable: Throwable? = null) =
        log(LogLevel.DEBUG, tag, message, throwable)

    fun i(message: String, tag: String? = null, throwable: Throwable? = null) =
        log(LogLevel.INFO, tag, message, throwable)

    fun w(message: String, tag: String? = null, throwable: Throwable? = null) =
        log(LogLevel.WARN, tag, message, throwable)

    fun e(message: String, tag: String? = null, throwable: Throwable? = null) =
        log(LogLevel.ERROR, tag, message, throwable)

    private fun log(
        level: LogLevel,
        tag: String?,
        message: String,
        throwable: Throwable?
    ) {
        if (!config.isEnabled) return
        if (level.priority < config.minLevel.priority) return

        val resolvedTag = tag ?: config.tag
        val finalMessage = buildMessage(message)

        logcatPrinter.print(level, resolvedTag, finalMessage, throwable)
    }

    private fun buildMessage(message: String): String {
        val sb = StringBuilder()

        if (config.showThreadInfo) {
            sb.append("[${Thread.currentThread().name}] ")
        }
        if (config.showCallerInfo) {
            val caller = Thread.currentThread().stackTrace
                .firstOrNull { it.className !in INTERNAL_CLASSES }
            caller?.let { sb.append("(${it.fileName}:${it.lineNumber}) ") }
        }
        sb.append(message)
        return sb.toString()
    }

    private val INTERNAL_CLASSES = setOf(
        Logger::class.java.name,
        "dalvik.system.VMStack",
        "java.lang.Thread"
    )
}