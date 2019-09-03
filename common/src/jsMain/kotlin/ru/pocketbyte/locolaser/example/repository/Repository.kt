package ru.pocketbyte.locolaser.example.repository

import ru.pocketbyte.locolaser.kmpp.StringRepository

actual object Repository {

    private var _str: StringRepository? = null

    actual val str: StringRepository
        get() = _str!!

    fun init(str: StringRepository) {
        if (_str != null)
            throw IllegalStateException("Repository already initialized")
        _str = str
    }


}