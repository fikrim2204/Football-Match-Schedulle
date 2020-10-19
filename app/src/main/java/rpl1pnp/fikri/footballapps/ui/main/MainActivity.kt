package rpl1pnp.fikri.footballapps.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import rpl1pnp.fikri.footballapps.R
import rpl1pnp.fikri.footballapps.adapter.MainAdapter
import rpl1pnp.fikri.footballapps.model.League
import rpl1pnp.fikri.footballapps.ui.league.LeagueActivity
import rpl1pnp.fikri.footballapps.ui.search.SearchActivity
import rpl1pnp.fikri.footballapps.util.invisible

class MainActivity : AppCompatActivity() {

    private var idLeague: String = ""
    private lateinit var adapter: MainAdapter
    private var items: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.app_name)
        progressbar_main.invisible()
        rv_main.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutCompat.VERTICAL)

        initLeague()
        adapter = MainAdapter(items) {
            idLeague = it.idLeague.toString()
            startActivity(intentFor<LeagueActivity>("idLeague" to idLeague).singleTop())
        }
        rv_main.adapter = adapter
    }

    private fun initLeague() {
        val id = resources.getIntArray(R.array.league_id)
        val photo = resources.obtainTypedArray(R.array.league_logo)
        val name = resources.getStringArray(R.array.league_name)

        items.clear()
        for (i in name.indices) {
            items.add(
                League(
                    id[i],
                    name[i],
                    photo.getResourceId(i, 0)
                )
            )
        }
        photo.recycle()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.favorite_navigation, menu)
        menuInflater.inflate(R.menu.search_navigation, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_navigation_search -> {
                startActivity(intentFor<SearchActivity>().singleTop())
                return true
            }
//            R.id.btn_navigation_favorite -> {
//                startActivity(intentFor<FavoriteActivity>().singleTop())
//            }
        }
        return super.onOptionsItemSelected(item)
    }

}
