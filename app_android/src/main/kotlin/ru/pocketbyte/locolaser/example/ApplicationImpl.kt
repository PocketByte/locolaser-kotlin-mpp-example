package ru.pocketbyte.locolaser.example

import android.app.Application
import ru.pocketbyte.locolaser.example.repository.AndroidStringRepository
import ru.pocketbyte.locolaser.example.repository.Repository

public class ApplicationImpl : Application() {

    override fun onCreate() {
        super.onCreate()

        // Init Repository
        Repository.initInstance(AndroidStringRepository(this))
    }
}