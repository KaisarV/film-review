package com.kai.filmreviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFilm: RecyclerView
    private val list = ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFilm = findViewById(R.id.rv_heroes)
        rvFilm.setHasFixedSize(true)

        list.addAll(getListFilm())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_about -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.action_exit -> {
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListFilm(): ArrayList<Film> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataSynopsis = resources.getStringArray(R.array.data_description)
        val dataPosterPhoto = resources.getStringArray(R.array.data_poster_photo)
        val dataBackdropPhoto = resources.getStringArray(R.array.data_backdrop_photo)
        val releaseDate = resources.getStringArray(R.array.release_date)
        val voteAvg = resources.getStringArray(R.array.vote_average)
        val voteCnt = resources.getStringArray(R.array.vote_count)
        val lang = resources.getStringArray(R.array.lang)
        val listFilm = ArrayList<Film>()
        for (i in dataName.indices) {
            val hero = Film(dataName[i], dataSynopsis[i], dataPosterPhoto[i], dataBackdropPhoto[i],
                releaseDate[i], voteAvg[i], voteCnt[i], lang[i])
            listFilm.add(hero)
        }
        return listFilm
    }

    private fun showRecyclerList() {
        rvFilm.layoutManager = GridLayoutManager(this, 2)
        val listFilmAdapter = ListFilmAdapter(list)
        rvFilm.adapter = listFilmAdapter

        listFilmAdapter.setOnItemClickCallback(object : ListFilmAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Film) {
                val film = Film(
                    data.name,
                    data.synopsis,
                    data.posterImage,
                    data.backdropImage,
                    data.releaseDate,
                    data.voteAverage,
                    data.voteCount,
                    data.originalLanguage
                )
                val moveWithObjectIntent = Intent(this@MainActivity, FilmDetailActivity::class.java)
                moveWithObjectIntent.putExtra(FilmDetailActivity.EXTRA_FILM, film)
                startActivity(moveWithObjectIntent)
            }
        })
    }

    private fun showSelectedFilm(hero: Film) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
}