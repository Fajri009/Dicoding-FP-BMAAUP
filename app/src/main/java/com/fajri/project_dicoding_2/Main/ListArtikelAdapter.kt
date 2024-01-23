package com.fajri.project_dicoding_2.Main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fajri.project_dicoding_2.R

class ListArtikelAdapter(
    private val listArtikel: ArrayList<Artikel>
): RecyclerView.Adapter<ListArtikelAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvJudul: TextView = itemView.findViewById(R.id.tv_item_judul)
        val tvIsi: TextView = itemView.findViewById(R.id.tv_item_isi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_artikel, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo, judul, isi) = listArtikel[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvJudul.text = judul
        holder.tvIsi.text = isi
        holder.itemView.setOnClickListener {
            onItemClickCallBack.onItemClicked(listArtikel[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listArtikel.size

    interface OnItemClickCallBack {
        fun onItemClicked(data: Artikel)
    }
}