package ru.geekbrains.androidhomework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.androidhomework.App
import ru.geekbrains.androidhomework.R
import ru.geekbrains.androidhomework.di.user.UsersSubcomponent
import ru.geekbrains.androidhomework.mvp.presenter.UsersPresenter
import ru.geekbrains.androidhomework.ui.IBackButtonListener
import ru.geekbrains.androidhomework.ui.adapter.UsersRVAdapter
import ru.geekbrains.androidhomework.mvp.view.IUsersView

class UsersFragment : MvpAppCompatFragment(), IUsersView, IBackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    var userSubcomponent: UsersSubcomponent? = null

    val presenter: UsersPresenter by moxyPresenter {
        userSubcomponent = App.instance.initUsersSubcomponent()
        UsersPresenter().apply {
            userSubcomponent?.inject(this)
        }
    }

    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
         rv_users.layoutManager = LinearLayoutManager(context)
         adapter = UsersRVAdapter(presenter.usersListPresenter).apply {
             userSubcomponent?.inject(this)
             }
         rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    override fun release() {
        userSubcomponent = null
        App.instance.releaseUsersSubcomponent()
    }

}
