package ru.pocketbyte.locolaser.example.repository

public class Repository {

    companion object {

        private var stringRepository: StringRepository? = null

        public fun initInstance(stringRepository: StringRepository) {
            Companion.stringRepository = stringRepository
        }

        val str: StringRepository
            get() = stringRepository!!
    }
}