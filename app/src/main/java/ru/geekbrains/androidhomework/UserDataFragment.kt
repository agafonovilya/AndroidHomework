package ru.geekbrains.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_data.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.androidhomework.model.entity.GithubUser
import ru.geekbrains.androidhomework.model.entity.GithubUsersRepo
import ru.geekbrains.androidhomework.presenter.UserDataPresenter
import ru.geekbrains.androidhomework.view.BackButtonListener
import ru.geekbrains.androidhomework.view.UserDataView

class UserDataFragment() : MvpAppCompatFragment(), UserDataView, BackButtonListener {
        companion object {
            lateinit var currentUser: GithubUser
            fun newInstance(user: GithubUser): UserDataFragment {
                currentUser = user
                return UserDataFragment()
            }

        }

        val presenter: UserDataPresenter by moxyPresenter { UserDataPresenter(currentUser, App.instance.router) }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            View.inflate(context, R.layout.fragment_user_data, null)

    override fun setLogin(text : String) {
        tv_user_data.text = text
    }

    override fun backPressed() = presenter.backPressed()

}