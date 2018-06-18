package ru.pocketbyte.locolaser.example.presentation

import ru.pocketbyte.locolaser.example.repository.Repository

public class MainScreenPresenter(
        private val view: MainScreenContract.View
): MainScreenContract.Presenter {


    override fun start() {
        this.view.showMessage(Repository.str.screen_main_hello_text)
    }
}