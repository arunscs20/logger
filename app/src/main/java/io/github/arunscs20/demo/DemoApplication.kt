package io.github.arunscs20.demo

import android.app.Application
import io.github.arunscs20.logger.LogLevel
import io.github.arunscs20.logger.Logger

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.init(this) {
            //we can change our configs here
            minLevel = LogLevel.DEBUG
        }
    }
}