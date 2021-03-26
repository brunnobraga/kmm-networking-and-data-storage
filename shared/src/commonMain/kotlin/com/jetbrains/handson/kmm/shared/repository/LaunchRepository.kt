package com.jetbrains.handson.kmm.shared.repository

import com.jetbrains.handson.kmm.shared.entity.RocketLaunch
import org.kodein.db.*
import org.kodein.db.impl.open
import org.kodein.db.orm.kotlinx.KotlinxSerializer
import org.kodein.memory.use

class LaunchRepository(location: RepositoryLocation) {
    private val db: DB = DB.open(
        location.appDababaseFilePath(),
        KotlinxSerializer {
            +RocketLaunch.serializer()
        })

    fun getAllLaunches(): List<RocketLaunch> {
        return db.find<RocketLaunch>().all().asModelSequence().toList()
    }

    fun clearLaunches() {

        db.find<RocketLaunch>().all().use {
            db.execBatch {
                deleteAll(it)
            }
        }
    }

    fun createLaunches(launches: List<RocketLaunch>) {
        db.execBatch {
            launches.forEach { put(it) }
        }
    }
}

expect class RepositoryLocation {
    fun appDababaseFilePath(): String
}