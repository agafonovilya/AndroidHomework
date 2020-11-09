package ru.geekbrains.androidhomework.presenter

import moxy.MvpPresenter
import ru.geekbrains.androidhomework.Screens
import ru.geekbrains.androidhomework.model.entity.GithubUser
import ru.geekbrains.androidhomework.model.entity.GithubUsersRepo
import ru.geekbrains.androidhomework.presenter.list.IUserListPresenter
import ru.geekbrains.androidhomework.view.UserItemView
import ru.geekbrains.androidhomework.view.UsersView
import ru.terrakok.cicerone.Router

const val TAG = "Github Client"

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.UserDataScreen(usersListPresenter.users[itemView.pos]))
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
