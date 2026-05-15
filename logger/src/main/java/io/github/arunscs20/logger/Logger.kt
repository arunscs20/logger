package io.github.arunscs20.logger

import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.Log

object Logger {

    private var config = LogConfig()
    private val logcatPrinter = LogcatPrinter()
    private var isDebugBuild = false

    /**
     * Initializes the logging library with the provided [context] and an optional
     * configuration [block].
     *
     * This should ideally be called once in the Application class. It determines
     * the build environment (Debug vs Release) and sets up the [LogConfig]
     * parameters using a DSL-style configuration.
     *
     * ### Example Usage:
     * ```
     * LogManager.init(context) {
     *     tag = "MyPlugin"
     *     minLevel = LogLevel.ERROR
     *     debugOnly = true
     * }
     */
    fun init(context: Context, block: LogConfig.() -> Unit = {}) {
        config = LogConfig().apply(block)
        isDebugBuild = isDebugBuild(context)
    }

    /**
     * Logs a VERBOSE message. Use this for the most detailed, noisy diagnostic information.
     */
    fun v(tag: String? = null, message: String, throwable: Throwable? = null) =
        log(LogLevel.VERBOSE, tag, message, throwable, isDebugBuild)

    /**
     * Logs a DEBUG message. Use this for information useful during development to troubleshoot issues.
     */
    fun d(tag: String? = null, message: String, throwable: Throwable? = null) =
        log(LogLevel.DEBUG, tag, message, throwable, isDebugBuild)

    /**
     * Logs an INFO message. Use this for high-level tracking of application state or milestones.
     */
    fun i(tag: String? = null, message: String, throwable: Throwable? = null) =
        log(LogLevel.INFO, tag, message, throwable, isDebugBuild)

    /**
     * Logs a WARN message. Use this for unexpected events that aren't errors but deserve attention.
     */
    fun w(tag: String? = null, message: String, throwable: Throwable? = null) =
        log(LogLevel.WARN, tag, message, throwable, isDebugBuild)

    /**
     * Logs an ERROR message. Use this for issues that cause a failure in a specific operation.
     */
    fun e(tag: String? = null, message: String, throwable: Throwable? = null) =
        log(LogLevel.ERROR, tag, message, throwable, isDebugBuild)

    /**
     * Logs an ASSERT message. Use this for "What a Terrible Failure" (WTF) conditions that should never happen.
     */
    fun a(tag: String? = null, message: String, throwable: Throwable? = null) =
        log(LogLevel.ASSERT, tag, message, throwable, isDebugBuild)

    private fun log(
        level: LogLevel,
        tag: String?,
        message: String,
        throwable: Throwable?,
        isDebugBuild: Boolean,
    ) {
        if (!config.isEnabled) return
        if (!isDebugBuild && config.debugOnly) return
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
            val caller =
                Thread.currentThread().stackTrace.firstOrNull { it.className !in INTERNAL_CLASSES }
            caller?.let { sb.append("(${it.fileName}:${it.lineNumber}) ") }
        }
        sb.append(message)
        return sb.toString()
    }

    private val INTERNAL_CLASSES = setOf(
        Logger::class.java.name, "dalvik.system.VMStack", "java.lang.Thread"
    )

    private fun isDebugBuild(context: Context): Boolean {
        val application = context.applicationContext
        return application.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }
}