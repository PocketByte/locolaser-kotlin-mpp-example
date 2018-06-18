package ru.pocketbyte.locolaser.example.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.pocketbyte.locolaser.example.R
import ru.pocketbyte.locolaser.example.presentation.MainScreenContract
import ru.pocketbyte.locolaser.example.presentation.MainScreenPresenter

public class MainActivity : AppCompatActivity(), MainScreenContract.View {

    private var presenter: MainScreenContract.Presenter = MainScreenPresenter(this)

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.presenter.start()
    }

    override fun showMessage(message: String) {
        txtHello.text = message
    }
}
