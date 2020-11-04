package ru.geekbrains.androidhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ru.geekbrains.androidhomework.presenter.MainPresenter
import ru.geekbrains.androidhomework.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_counter1.setOnClickListener { presenter.btn1Click() }
        btn_counter2.setOnClickListener { presenter.btn2Click() }
        btn_counter3.setOnClickListener { presenter.btn3Click() }
    }

    override fun setButton1Text(text: String) {
        btn_counter1.text = text
    }

    override fun setButton2Text(text: String) {
        btn_counter2.text = text
    }

    override fun setButton3Text(text: String) {
        btn_counter3.text = text
    }
}
