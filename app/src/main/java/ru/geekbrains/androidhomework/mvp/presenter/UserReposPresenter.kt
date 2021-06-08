package ru.geekbrains.androidhomework.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.androidhomework.navigation.Screens
import ru.geekbrains.androidhomework.mvp.model.entity.GithubRepository

import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.androidhomework.mvp.presenter.list.IUserReposListPresenter
import ru.geekbrains.androidhomework.mvp.view.list.IUserReposItemView
import ru.geekbrains.androidhomework.mvp.view.IUserReposView
import ru.terrakok.cicerone.Router


class UserReposPresenter(private val mainThreadScheduler: Scheduler,
                         private val user : GithubUser,
                         private val usersRepo: IGithubRepositoriesRepo,
                         private val router : Router) : MvpPresenter<IUserReposView>() {

    class UserReposListPresenter : IUserReposListPresenter {
        val repos = mutableListOf<GithubRepository>()
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

            usersRepo.getRepos(user)
                .observeOn(mainThreadScheduler)
                .subscribe({ repos ->
                    userReposListPresenter.repos.clear()
                    userReposListPresenter.repos.addAll(repos)
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
