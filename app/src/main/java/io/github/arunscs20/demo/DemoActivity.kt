package io.github.arunscs20.demo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.arunscs20.logger.Logger

class DemoActivity : AppCompatActivity() {
    private val tag = "Demo activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Logger.v(tag, "This is verbose")
        Logger.d(tag, "This is debug")
        Logger.w(tag, "This is warning")
        Logger.e(tag, "This is error")
        Logger.i(tag, "This is info")
        Logger.a(tag, "This is assert")
    }
}