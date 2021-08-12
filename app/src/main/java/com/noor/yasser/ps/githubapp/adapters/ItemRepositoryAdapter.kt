package com.noor.yasser.ps.githubapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.databinding.ItemReposRepoBinding
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem

class ItemRepositoryAdapter(val itemclick: OnListItemViewClickListener) :
    RecyclerView.Adapter<ItemRepositoryAdapter.ItemRepositoryViewHolder>() {


    inner class ItemRepositoryViewHolder(val mBinding: ItemReposRepoBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun onBind(data: RepositoryItem) {
            mBinding.data = data;
            mBinding.root.setOnClickListener {
                itemclick.onClickItem(data, 1)
            }
            itemclick.onChangeColorInserted(mBinding.ivStarRoom, data)
            mBinding.ivStarRoom.setOnClickListener {
                itemclick.onClickStart(data)
                itemclick.onChangeColorInserted(mBinding.ivStarRoom, data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRepositoryViewHolder {
        return ItemRepositoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_repos_repo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemRepositoryViewHolder, position: Int) {
        holder.onBind(data = data[position])
    }

    override fun getItemCount() = data.size

    interface OnListItemViewClickListener {
        fun onClickItem(itemViewModel: RepositoryItem, type: Int)
        fun onClickStart(item: RepositoryItem)
        fun onChangeColorInserted(imageView: ImageView, item: RepositoryItem)
    }

    val diffCallback = object : DiffUtil.ItemCallback<RepositoryItem>() {
        override fun areItemsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
    val differ = AsyncListDiffer(this, diffCallback)

    var data: List<RepositoryItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)


}