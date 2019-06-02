package ru.pocketbyte.locolaser.example.presentation

import ru.pocketbyte.locolaser.example.repository.Repository

public class MainScreenPresenter(
        private val view: MainScreenContract.View
): MainScreenContract.Presenter {

    override fun start() {
        this.view.showMessage1(Repository.str.screen_main_hello_text)
        this.view.showMessage2(Repository.str.screen_main_plural_string(1))
    }
}