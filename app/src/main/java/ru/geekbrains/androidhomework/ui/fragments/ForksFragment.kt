package ru.geekbrains.androidhomework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_forks.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.androidhomework.App
import ru.geekbrains.androidhomework.R
import ru.geekbrains.androidhomework.mvp.model.entity.GithubRepository
import ru.geekbrains.androidhomework.mvp.presenter.ForksPresenter
import ru.geekbrains.androidhomework.ui.IBackButtonListener
import ru.geekbrains.androidhomework.mvp.view.IForksView

class ForksFragment: MvpAppCompatFragment(), IForksView, IBackButtonListener {
    companion object {
        lateinit var repo: GithubRepository
        fun newInstance(repo: GithubRepository): ForksFragment {
            Companion.repo = repo
            return ForksFragment()
        }
    }

    val presenter: ForksPresenter by moxyPresenter{ForksPresenter(repo, App.instance.router)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        View.inflate(context, R.layout.fragment_forks, null)

    override fun backPressed() = presenter.backPressed()


    override fun setForksQuantity(quantity: String) {
        tv_fork_quantity.text = quantity
    }
}