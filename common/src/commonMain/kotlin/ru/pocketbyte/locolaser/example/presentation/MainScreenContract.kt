package ru.pocketbyte.locolaser.example.presentation

interface MainScreenContract {

    interface View {
        fun showMessage1(message: String)
        fun showMessage2(message: String)
        fun showMessage3(message: String)
    }

    interface Presenter {
        fun start()
    }

}