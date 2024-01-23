package com.fajri.project_dicoding_2.Main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artikel(
    val photo: String,
    val judul: String,
    val isi: String
): Parcelable