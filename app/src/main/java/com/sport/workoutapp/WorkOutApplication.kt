package com.sport.workoutapp

import android.app.Application
import com.sport.workoutapp.data.AudioPlayer
import com.sport.workoutapp.data.Repository
import com.sport.workoutapp.data.model.Day
import com.sport.workoutapp.data.model.Exercise
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class WorkOutApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()

        initRealm()
        checkFirstLaunchAndCreateSampleDays()

        player = AudioPlayer(this)
    }

    private fun initRealm() {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Day::class,
                Exercise::class
            )
        )
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()

        realm = Realm.open(config)
    }

    private fun checkFirstLaunchAndCreateSampleDays() {
        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

        if (isFirstLaunch) {
            applicationScope.launch {
                Repository.createSampleEntries(this@launch)
                sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
            }
        }
    }

    companion object {
        lateinit var realm: Realm
        lateinit var player: AudioPlayer
    }
}