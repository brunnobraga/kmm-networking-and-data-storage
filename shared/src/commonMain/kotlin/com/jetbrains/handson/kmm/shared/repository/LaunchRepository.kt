package com.jetbrains.handson.kmm.shared.repository

import com.jetbrains.handson.kmm.shared.entity.RocketLaunch
import org.kodein.db.*
import org.kodein.db.impl.open
import org.kodein.db.orm.kotlinx.KotlinxSerializer

class LaunchRepository(location: RepositoryLocation) {
    private val db: DB = DB.open(
        location.appDababaseFilePath(),
        KotlinxSerializer {
            register(RocketLaunch::class, RocketLaunch.serializer())
        })

    fun getAllLaunches(): List<RocketLaunch> {
        return db.find(RocketLaunch::class).all().asModelSequence().toList()
    }

    fun clearLaunches() {
        db.find(RocketLaunch::class).all().asKeySequence().toList().forEach {
            db.delete(RocketLaunch::class, it)
        }
    }

    fun createLaunches(launches: List<RocketLaunch>) {
        launches.forEach { db.put(it) }
    }
}

expect class RepositoryLocation {
    fun appDababaseFilePath(): String
}