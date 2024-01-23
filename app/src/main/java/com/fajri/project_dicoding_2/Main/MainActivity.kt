package com.fajri.project_dicoding_2.Main

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fajri.project_dicoding_2.About.AboutScreen
import com.fajri.project_dicoding_2.Detail.DetailScreen
import com.fajri.project_dicoding_2.R

class MainActivity : AppCompatActivity() {
    private lateinit var ivProfile: ImageView
    private lateinit var rvArtikel: RecyclerView
    private val list = ArrayList<Artikel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivProfile = findViewById(R.id.iv_profile)
        rvArtikel = findViewById(R.id.rv_artikel)
        rvArtikel.setHasFixedSize(true)

        onClickIvProfile()

        list.addAll(getListArtikel())
        showRecyclerList()
    }

    private fun onClickIvProfile() {
        ivProfile.setOnClickListener {
            val intentProfile = Intent(this, AboutScreen::class.java)
            startActivity(intentProfile)
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
        rvArtikel.layoutManager = LinearLayoutManager(this)
        val listArtikelAdapter = ListArtikelAdapter(list)
        rvArtikel.adapter = listArtikelAdapter

        listArtikelAdapter.setOnItemClickCallBack(object: ListArtikelAdapter.OnItemClickCallBack {
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