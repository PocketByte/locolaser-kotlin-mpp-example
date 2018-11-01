package ru.pocketbyte.locolaser.example.repository

actual object Repository {

    private var stringRepository: StringRepository? = null

    public fun initInstance(stringRepository: StringRepository) {
        if (this.stringRepository != null)
            throw IllegalStateException("Repository already initialized")
        this.stringRepository = stringRepository
    }

    actual val str: StringRepository
        get() = this.stringRepository!!

}