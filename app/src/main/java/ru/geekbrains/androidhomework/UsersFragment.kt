package ru.geekbrains.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.androidhomework.model.repo.retrofit.RetrofitGithubUsersRepo
import ru.geekbrains.androidhomework.presenter.UsersPresenter
import ru.geekbrains.androidhomework.view.IBackButtonListener
import ru.geekbrains.androidhomework.view.UsersRVAdapter
import ru.geekbrains.androidhomework.view.IUsersView
import ru.geekbrains.androidhomework.view.image.GlideImageLoader

class UsersFragment : MvpAppCompatFragment(), IUsersView, IBackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter { UsersPresenter(AndroidSchedulers.mainThread(),
        RetrofitGithubUsersRepo(ApiHolder.api), App.instance.router) }

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
