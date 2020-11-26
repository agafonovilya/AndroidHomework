package ru.geekbrains.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_forks.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.androidhomework.model.entity.GithubRepo
import ru.geekbrains.androidhomework.presenter.ForksPresenter
import ru.geekbrains.androidhomework.view.IBackButtonListener
import ru.geekbrains.androidhomework.view.IForksView

class ForksFragment: MvpAppCompatFragment(), IForksView, IBackButtonListener {
    companion object {
        lateinit var repo: GithubRepo
        fun newInstance(repo: GithubRepo): ForksFragment {
            this.repo = repo
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