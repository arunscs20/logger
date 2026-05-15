# Logger

A lightweight, modern, and developer-friendly logging library for Kotlin and Android projects.

Designed to provide clean, structured, and customizable logs with minimal setup.

---

## ✨ Features

* 🚀 Simple and clean API
* 🏷️ Multiple log levels
* 🧩 Easy integration with Android & Kotlin projects
* ⚡ Lightweight and fast
* 🛠️ Customizable logger configuration
* 📦 Maven Central support
* 🔍 Helpful debug formatting

---

## 📦 Installation

### Gradle (Kotlin DSL)

```kotlin
implementation("io.github.arunscs20:logger:<latest-version>")
```

### Gradle (Groovy)

```groovy
implementation 'io.github.arunscs20:logger:<latest-version>'
```

---

## 🚀 Quick Start

### Basic Usage

```kotlin
Logger.d("Debug message")
Logger.i("Info message")
Logger.w("Warning message")
Logger.e("Error message")
Logger.v("Verbose message")
```

## ⚙️ Configuration

Initialize the logger once, preferably inside your Application class.

All configuration options are optional.
```kotlin
Logger.init(context) {
    enabled = true
    showTimestamp = true
    showThreadInfo = true
    showTag = true
}
```

### Recommended Setup
```kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Logger.init(this)
    }
}
```
---

## 🏷️ Log Levels

| Level | Description        |
| ----- | ------------------ |
| `v()` | Verbose logs       |
| `d()` | Debug logs         |
| `i()` | Informational logs |
| `w()` | Warning logs       |
| `e()` | Error logs         |


---

## 📱 Android Example

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Logger.i("Application started")
    }
}
```

---

## 🛠️ Proguard / R8

No additional rules required.

---

## 📚 Use Cases

* Android applications
* Kotlin libraries
* Debugging network calls
* App lifecycle tracking
* Performance monitoring
* Development tooling

---

## 📄 License

```text
MIT License
```

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome.

Feel free to open an issue or submit a pull request.

---

## 🔗 Repository

GitHub: [https://github.com/arunscs20/logger](https://github.com/arunscs20/logger)

---

## ⭐ Support

If you find this project useful, consider giving it a star on GitHub ⭐
