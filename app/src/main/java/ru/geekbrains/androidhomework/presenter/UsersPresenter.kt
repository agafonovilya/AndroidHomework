package ru.geekbrains.androidhomework.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.androidhomework.Screens
import ru.geekbrains.androidhomework.model.entity.GithubUser
import ru.geekbrains.androidhomework.model.repo.IGithubUsersRepo
import ru.geekbrains.androidhomework.presenter.list.IUserListPresenter
import ru.geekbrains.androidhomework.view.IUserItemView
import ru.geekbrains.androidhomework.view.IUsersView
import ru.terrakok.cicerone.Router

const val TAG = "Github Client"

class UsersPresenter(
    private val mainThreadScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo,
    private val router: Router) : MvpPresenter<IUsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let {view.loadAvatar(it)}
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(Screens.UserReposScreen(user, usersRepo))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(it)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
