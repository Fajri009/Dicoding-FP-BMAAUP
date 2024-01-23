package com.fajri.project_dicoding_2.Detail

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fajri.project_dicoding_2.Main.Artikel
import com.fajri.project_dicoding_2.Main.ListArtikelAdapter
import com.fajri.project_dicoding_2.R

class DetailScreen : AppCompatActivity() {
    private lateinit var ivBack: ImageView
    private lateinit var ivGambar: ImageView
    private lateinit var tvJudul: TextView
    private lateinit var tvIsi: TextView
    private lateinit var rvDetailArtikel: RecyclerView
    private val list = ArrayList<Artikel>()

    companion object {
        const val EXTRA_ARTIKEL ="extra_artikel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)

        ivBack = findViewById(R.id.iv_back)
        ivGambar = findViewById(R.id.iv_gambar)
        tvJudul = findViewById(R.id.tv_judul)
        tvIsi = findViewById(R.id.tv_isi)
        rvDetailArtikel = findViewById(R.id.rv_artikel_column)
        rvDetailArtikel.setHasFixedSize(true)

        backButton()

        val artikel =
            if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra<Artikel>(EXTRA_ARTIKEL, Artikel::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra<Artikel>(EXTRA_ARTIKEL)
            }

        if (artikel != null) {
            Glide.with(this)
                .load(artikel.photo)
                .into(ivGambar)
            tvJudul.text = artikel.judul
            tvIsi.text = artikel.isi
        }

        list.addAll(getListArtikel())
        showRecyclerList()
    }

    private fun backButton() {
        ivBack.setOnClickListener {
            finish()
        }
    }

    private fun getListArtikel(): ArrayList<Artikel> {
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataJudul = resources.getStringArray(R.array.data_judul_artikel)
        val dataIsi = resources.getStringArray(R.array.data_isi_artikel)
        val listArtikel = ArrayList<Artikel>()

        for (i in dataJudul.indices) {
            val artikel = Artikel(dataPhoto[i], dataJudul[i], dataIsi[i])
            listArtikel.add(artikel)
        }
        return listArtikel
    }

    private fun showRecyclerList() {
        rvDetailArtikel.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listArtikelAdapter = ListDetailArtikelAdapter(list)
        rvDetailArtikel.adapter = listArtikelAdapter

        listArtikelAdapter.setOnItemClickCallBack(object: ListDetailArtikelAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Artikel) {
                showSelectedDetailArtikel(data)
            }
        })
    }

    private fun showSelectedDetailArtikel(artikel: Artikel) {
        val intentDetail = Intent(this, DetailScreen::class.java)
        intentDetail.putExtra(DetailScreen.EXTRA_ARTIKEL, artikel)
        startActivity(intentDetail)
    }
}