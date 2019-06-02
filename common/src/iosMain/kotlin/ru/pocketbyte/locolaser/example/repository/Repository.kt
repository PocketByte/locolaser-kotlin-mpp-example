package ru.pocketbyte.locolaser.example.repository

import ru.pocketbyte.locolaser.kmpp.IosStringRepository
import ru.pocketbyte.locolaser.kmpp.StringRepository

actual object Repository {

    actual val str: StringRepository = IosStringRepository()

}