package ru.geekbrains.androidhomework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_user_repos.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.androidhomework.App
import ru.geekbrains.androidhomework.R
import ru.geekbrains.androidhomework.di.repository.UserRepositoriesSubcomponent
import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser
import ru.geekbrains.androidhomework.mvp.presenter.UserRepositoriesPresenter
import ru.geekbrains.androidhomework.ui.IBackButtonListener
import ru.geekbrains.androidhomework.ui.adapter.UserRepositoriesRVAdapter
import ru.geekbrains.androidhomework.mvp.view.IUserReposView

class UserRepositoriesFragment: MvpAppCompatFragment(), IUserReposView, IBackButtonListener {

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserRepositoriesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    var repositorySubcomponent: UserRepositoriesSubcomponent? = null

    private var adapter: UserRepositoriesRVAdapter? = null

    val presenter: UserRepositoriesPresenter by moxyPresenter {
        repositorySubcomponent = App.instance.initUserRepositoriesSubcomponent()
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserRepositoriesPresenter(user).apply { repositorySubcomponent?.inject(this) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            View.inflate(context, R.layout.fragment_user_repos, null)

    override fun init() {
        rv_repos.layoutManager = LinearLayoutManager(context)
        adapter = UserRepositoriesRVAdapter(presenter.userReposListPresenter)
        rv_repos.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        repositorySubcomponent = null
        App.instance.releaseUserRepositoriesSubcomponent()
    }

    override fun backPressed() = presenter.backPressed()

}