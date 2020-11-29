package ru.geekbrains.androidhomework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.androidhomework.ApiHolder
import ru.geekbrains.androidhomework.App
import ru.geekbrains.androidhomework.R
import ru.geekbrains.androidhomework.mvp.model.entity.cache.IUserCache
import ru.geekbrains.androidhomework.mvp.model.entity.room.Database
import ru.geekbrains.androidhomework.mvp.model.entity.room.cache.RoomUserCache
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.androidhomework.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import ru.geekbrains.androidhomework.mvp.presenter.UsersPresenter
import ru.geekbrains.androidhomework.ui.IBackButtonListener
import ru.geekbrains.androidhomework.ui.adapter.UsersRVAdapter
import ru.geekbrains.androidhomework.mvp.view.IUsersView
import ru.geekbrains.androidhomework.mvp.view.image.GlideImageLoader
import ru.geekbrains.androidhomework.ui.network.AndroidNetworkStatus

class UsersFragment : MvpAppCompatFragment(), IUsersView, IBackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter(
        factory = fun(): UsersPresenter {
            val githubUsersRepo : IGithubUsersRepo = RetrofitGithubUsersRepo(
                ApiHolder.api,
                AndroidNetworkStatus(requireContext()),
                userCache = RoomUserCache(Database.getInstance())
            )
            return UsersPresenter(
                AndroidSchedulers.mainThread(),
                githubUsersRepo,
                App.instance.router
            )
    })

    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

}
