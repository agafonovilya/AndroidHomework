package ru.geekbrains.androidhomework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_user_repos.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.androidhomework.ApiHolder
import ru.geekbrains.androidhomework.App
import ru.geekbrains.androidhomework.R
import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser
import ru.geekbrains.androidhomework.mvp.model.entity.room.Database
import ru.geekbrains.androidhomework.mvp.model.entity.room.cache.RoomRepositoriesCache
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.androidhomework.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.presenter.UserReposPresenter
import ru.geekbrains.androidhomework.ui.IBackButtonListener
import ru.geekbrains.androidhomework.ui.adapter.UserReposRVAdapter
import ru.geekbrains.androidhomework.mvp.view.IUserReposView
import ru.geekbrains.androidhomework.ui.network.AndroidNetworkStatus

class UserReposFragment: MvpAppCompatFragment(), IUserReposView, IBackButtonListener {
    companion object {
        lateinit var currentUser: GithubUser
        fun newInstance(user: GithubUser): UserReposFragment {
            currentUser = user
            return UserReposFragment()
        }

    }

    val presenter: UserReposPresenter by moxyPresenter(
        factory = fun(): UserReposPresenter {
            val githubRepositoriesRepo : IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(
                ApiHolder.api,
                AndroidNetworkStatus(requireContext()),
                RoomRepositoriesCache(Database.getInstance())
            )

            return UserReposPresenter(
                AndroidSchedulers.mainThread(),
                currentUser,
                githubRepositoriesRepo,
                App.instance.router,
            )
        },
    )

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