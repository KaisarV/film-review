package com.kai.filmreviewapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class FilmDetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    private lateinit var buttonBackHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)

        val filmTitle: TextView = findViewById(R.id.film_title)
        val filmSynopsis:TextView = findViewById(R.id.synopsis)
        val filmRelease:TextView = findViewById(R.id.release_date)
        val voteAvg:TextView = findViewById(R.id.vote_avg)
        val voteCnt:TextView = findViewById(R.id.vote_cnt)
        val lan:TextView = findViewById(R.id.lan)
        val backdrop:ImageView = findViewById(R.id.img_backdrop)

        val film = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FILM, Film::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FILM)
        }

        if (film != null) {

            Glide.with(this)
                .load(film.backdropImage) // URL Gambar
                .into(backdrop) // imageView mana yang akan diterapkan

            filmTitle.text = film.name
            filmSynopsis.text = film.synopsis
            filmRelease.text = film.releaseDate
            voteAvg.text = film.voteAverage
            voteCnt.text = film.voteCount
            lan.text = film.originalLanguage
        }

        buttonBackHome = findViewById(R.id.back_home)
        buttonBackHome.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.back_home -> {
                onBackPressed()
            }
        }
    }
}