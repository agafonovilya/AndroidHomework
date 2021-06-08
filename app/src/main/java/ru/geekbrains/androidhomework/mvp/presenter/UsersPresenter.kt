package ru.geekbrains.androidhomework.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import moxy.MvpView
import ru.geekbrains.androidhomework.navigation.Screens
import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.androidhomework.mvp.presenter.list.IUserListPresenter
import ru.geekbrains.androidhomework.mvp.view.list.IUserItemView
import ru.geekbrains.androidhomework.mvp.view.IUsersView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

const val TAG = "Github Client"

class UsersPresenter(): MvpPresenter<IUsersView>() {

    @Inject lateinit var usersRepo: IGithubUsersRepo
    @Inject lateinit var router: Router
    @Inject lateinit var mainThreadScheduler: Scheduler

    constructor(_userRepo:IGithubUsersRepo, _router: Router, _mainThreadScheduler: Scheduler) : this() {
        usersRepo = _userRepo
        router = _router
        mainThreadScheduler = _mainThreadScheduler
    }

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
             router.navigateTo(Screens.UserReposScreen(user))
         }
     }

     fun loadData() {
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

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
 }
