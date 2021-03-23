package com.jetbrains.handson.kmm.shared.repository

import android.content.Context
import java.io.File

actual class RepositoryLocation(private val context: Context) {
    actual fun appDababaseFilePath(): String {
        return File(context.filesDir, "mydb").absolutePath
    }
}