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
       private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepository) = ForksFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

   val presenter: ForksPresenter by moxyPresenter {
       val repository = arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository
       ForksPresenter(repository).apply { App.instance.appComponent.inject(this) }
   }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        View.inflate(context, R.layout.fragment_forks, null)

    override fun backPressed() = presenter.backPressed()


    override fun setForksQuantity(quantity: String) {
        tv_fork_quantity.text = quantity
    }
}