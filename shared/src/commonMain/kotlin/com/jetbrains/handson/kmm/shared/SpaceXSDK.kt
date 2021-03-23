package com.jetbrains.handson.kmm.shared

import com.jetbrains.handson.kmm.shared.entity.RocketLaunch
import com.jetbrains.handson.kmm.shared.network.SpaceXApi
import com.jetbrains.handson.kmm.shared.repository.LaunchRepository
import com.jetbrains.handson.kmm.shared.repository.RepositoryLocation

class SpaceXSDK (location: RepositoryLocation) {
    private val api = SpaceXApi()
    private val repo = LaunchRepository(location)

    @Throws(Exception::class) suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = repo.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                repo.clearLaunches()
                repo.createLaunches(it)
            }
        }
    }
}
