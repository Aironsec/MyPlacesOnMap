package ru.serget.myplacesonmap.view.screens.myplaces

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.serget.myplacesonmap.databinding.AdapterRecycleviewMyplaceBinding
import ru.serget.myplacesonmap.model.data.ItemPlace

class MyPlaceAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<MyPlaceAdapter.RecyclerItemViewHolder>() {

    private var myPlaces: List<ItemPlace> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(myPlaces: List<ItemPlace>){
        this.myPlaces = myPlaces
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val itemBinding = AdapterRecycleviewMyplaceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return RecyclerItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(myPlaces[position])
    }

    override fun getItemCount(): Int = myPlaces.size

    inner class RecyclerItemViewHolder(private val itemBinding: AdapterRecycleviewMyplaceBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(itemPlace: ItemPlace) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemBinding.placeTitle.text = itemPlace.title
                itemBinding.placeDescription.text = itemPlace.description
                itemView.setOnClickListener { openEditDialog(itemPlace, layoutPosition) }
            }
        }
    }

    fun openEditDialog(itemPlace: ItemPlace, position: Int) {
        itemClickListener.clickItem(itemPlace, position)
    }

    interface ItemClickListener {
        fun clickItem(itemPlace: ItemPlace, position: Int)
    }
}