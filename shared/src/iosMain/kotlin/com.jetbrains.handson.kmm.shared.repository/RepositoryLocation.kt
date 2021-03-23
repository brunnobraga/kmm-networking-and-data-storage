package com.jetbrains.handson.kmm.shared.repository

import platform.Foundation.*

actual class RepositoryLocation {
    actual fun appDababaseFilePath(): String {
        val folder = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, true)[0] as String
        return "$folder/mydb"
    }
}