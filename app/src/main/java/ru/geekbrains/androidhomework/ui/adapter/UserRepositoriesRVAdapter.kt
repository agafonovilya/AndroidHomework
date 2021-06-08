package ru.geekbrains.androidhomework.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.view.*
import ru.geekbrains.androidhomework.R
import ru.geekbrains.androidhomework.mvp.presenter.list.IUserRepositoriesListPresenter
import ru.geekbrains.androidhomework.mvp.view.list.IUserRepositoryItemView

class UserRepositoriesRVAdapter(val presenter: IUserRepositoriesListPresenter) :
    RecyclerView.Adapter<UserRepositoriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener{
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, IUserRepositoryItemView {

        override var pos = -1

        override fun setRepoName(repoName: String) = with(containerView) {
            tv_repo_name.text = repoName
        }

    }
}