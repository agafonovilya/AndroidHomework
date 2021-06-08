package ru.geekbrains.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_user_repos.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.androidhomework.model.entity.GithubUser
import ru.geekbrains.androidhomework.model.repo.IGithubUsersRepo
import ru.geekbrains.androidhomework.presenter.UserReposPresenter
import ru.geekbrains.androidhomework.view.IBackButtonListener
import ru.geekbrains.androidhomework.view.UserReposRVAdapter
import ru.geekbrains.androidhomework.view.IUserReposView

class UserReposFragment: MvpAppCompatFragment(), IUserReposView, IBackButtonListener {
    companion object {
        lateinit var currentUser: GithubUser
        lateinit var usersRepo: IGithubUsersRepo
        fun newInstance(user: GithubUser, usersRepo: IGithubUsersRepo): UserReposFragment {
            this.usersRepo = usersRepo
            currentUser = user
            return UserReposFragment()
        }

    }

    val presenter: UserReposPresenter by moxyPresenter {
            UserReposPresenter(AndroidSchedulers.mainThread(),
            currentUser,
            usersRepo,
            App.instance.router) }

    private var adapter: UserReposRVAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            View.inflate(context, R.layout.fragment_user_repos, null)

    override fun init() {
        rv_repos.layoutManager = LinearLayoutManager(context)
        adapter = UserReposRVAdapter(presenter.userReposListPresenter)
        rv_repos.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

}