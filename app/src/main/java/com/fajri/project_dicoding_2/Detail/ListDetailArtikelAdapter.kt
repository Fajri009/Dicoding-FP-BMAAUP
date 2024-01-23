package com.fajri.project_dicoding_2.Detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fajri.project_dicoding_2.Main.Artikel
import com.fajri.project_dicoding_2.R

class ListDetailArtikelAdapter(
    private val listArtikel: ArrayList<Artikel>
): RecyclerView.Adapter<ListDetailArtikelAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallBack: ListDetailArtikelAdapter.OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: ListDetailArtikelAdapter.OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_column_gambar)
        val tvJudul: TextView = itemView.findViewById(R.id.tv_item_column_judul)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListDetailArtikelAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_column_artikel, parent, false)
        return ListDetailArtikelAdapter.ListViewHolder(view)
    }

    override fun getItemCount(): Int = listArtikel.size

    override fun onBindViewHolder(holder: ListDetailArtikelAdapter.ListViewHolder, position: Int) {
        val (photo, judul) = listArtikel[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvJudul.text = judul
        holder.itemView.setOnClickListener {
            onItemClickCallBack.onItemClicked(listArtikel[holder.adapterPosition])
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: Artikel)
    }
}