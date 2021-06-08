package ru.geekbrains.androidhomework.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.androidhomework.navigation.Screens
import ru.geekbrains.androidhomework.mvp.model.entity.GithubRepository

import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.presenter.list.IUserRepositoriesListPresenter
import ru.geekbrains.androidhomework.mvp.view.list.IUserRepositoryItemView
import ru.geekbrains.androidhomework.mvp.view.IUserReposView
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class UserRepositoriesPresenter(private val user : GithubUser) : MvpPresenter<IUserReposView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var mainThreadScheduler: Scheduler
    @Inject lateinit var usersRepo: IGithubRepositoriesRepo

    class UserRepositoriesListPresenter : IUserRepositoriesListPresenter {
        val repos = mutableListOf<GithubRepository>()
        override var itemClickListener: ((IUserRepositoryItemView) -> Unit)? = null
        override fun getCount() = repos.size

        override fun bindView(view: IUserRepositoryItemView) {
            val repo = repos[view.pos]
            repo.name?.let{ view.setRepoName(it)}
        }
    }

    val userReposListPresenter = UserRepositoriesListPresenter()

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

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
}
