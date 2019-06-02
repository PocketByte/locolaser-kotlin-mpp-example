package ru.pocketbyte.locolaser.example

import android.app.Application
import ru.pocketbyte.locolaser.example.repository.Repository
import ru.pocketbyte.locolaser.kmpp.AndroidStringRepository

public class ApplicationImpl : Application() {

    override fun onCreate() {
        super.onCreate()

        // 5.1 Init Android Repository
        Repository.initInstance(AndroidStringRepository(this))
    }
}