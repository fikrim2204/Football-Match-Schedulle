package rpl1pnp.fikri.footballmatchschedule.view

import rpl1pnp.fikri.footballmatchschedule.model.Events

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetail(data: List<Events>)
//    fun getLogoTeam(data: List<Team>, isHomeTeam: Boolean)
}