package ru.pocketbyte.locolaser.example.presentation

interface MainScreenContract {

    interface View {
        fun showMessage(message: String)
    }

    interface Presenter {
        fun start()
    }

}