package ru.pocketbyte.locolaser.example.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.pocketbyte.locolaser.example.R
import ru.pocketbyte.locolaser.example.presentation.MainScreenContract
import ru.pocketbyte.locolaser.example.presentation.MainScreenPresenter

class MainActivity : AppCompatActivity(), MainScreenContract.View {

    private var presenter: MainScreenContract.Presenter = MainScreenPresenter(this)

    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private lateinit var text3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)
        text3 = findViewById(R.id.text3)

        this.presenter.start()
    }

    override fun showMessage1(message: String) {
        text1.text = message
    }

    override fun showMessage2(message: String) {
        text2.text = message
    }

    override fun showMessage3(message: String) {
        text3.text = message
    }
}
