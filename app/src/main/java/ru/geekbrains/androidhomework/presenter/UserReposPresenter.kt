package ru.geekbrains.androidhomework.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.androidhomework.Screens
import ru.geekbrains.androidhomework.model.entity.GithubRepo

import ru.geekbrains.androidhomework.model.entity.GithubUser
import ru.geekbrains.androidhomework.model.repo.IGithubUsersRepo
import ru.geekbrains.androidhomework.presenter.list.IUserReposListPresenter
import ru.geekbrains.androidhomework.view.IUserReposItemView
import ru.geekbrains.androidhomework.view.IUserReposView
import ru.terrakok.cicerone.Router


class UserReposPresenter(private val mainThreadScheduler: Scheduler,
                         private val user : GithubUser,
                         private val usersRepo: IGithubUsersRepo,
                         private val router : Router) : MvpPresenter<IUserReposView>() {

    class UserReposListPresenter : IUserReposListPresenter {
        val repos = mutableListOf<GithubRepo>()
        override var itemClickListener: ((IUserReposItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: IUserReposItemView) {
            val repo = repos[view.pos]
            repo.name?.let{ view.setRepoName(it)}
        }
    }

    val userReposListPresenter = UserReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadRepos()

        userReposListPresenter.itemClickListener = { itemView ->
            val repo = userReposListPresenter.repos[itemView.pos]
            router.navigateTo(Screens.ForksScreen(repo))
        }
    }

    private fun loadRepos() {
        user.reposUrl?.let {
            usersRepo.getRepos(it)
                .observeOn(mainThreadScheduler)
                .subscribe({ repos ->
                    userReposListPresenter.repos.clear()
                    userReposListPresenter.repos.addAll(repos)
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
                })
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
