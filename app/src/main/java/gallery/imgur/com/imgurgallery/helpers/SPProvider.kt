package gallery.imgur.com.imgurgallery.helpers

import android.content.Context
import android.preference.PreferenceManager
import gallery.imgur.com.imgurgallery.R
import gallery.imgur.com.imgurgallery.adapters.RecyclerViewLayout

/**
 * Created by GAAN on 02.11.2017.
 */
class SPProvider {
    companion object {
        fun getSort(context : Context) : String{
            val SP = PreferenceManager.getDefaultSharedPreferences(context)
            return SP.getString(context.getString(R.string.pref_sort), "viral")
        }

        fun getWindow(context : Context) : String{
            val SP = PreferenceManager.getDefaultSharedPreferences(context)
            return SP.getString(context.getString(R.string.pref_window), "day")
        }

        fun getSection(context : Context) : String{
            val SP = PreferenceManager.getDefaultSharedPreferences(context)
            return SP.getString(context.getString(R.string.pref_gallery_section), "hot")
        }

        fun getShowViral(context : Context) : Boolean{
            val SP = PreferenceManager.getDefaultSharedPreferences(context)
            return SP.getBoolean(context.getString(R.string.pref_include_viral_image), true)
        }

        fun getRecyclerViewLayout(context : Context) : RecyclerViewLayout {
            val SP = PreferenceManager.getDefaultSharedPreferences(context)
            return RecyclerViewLayout.valueOf(SP.getString(context.getString(R.string.pref_rec_view_layout), RecyclerViewLayout.ListView.name))
        }

        fun writeRecyclerViewLayout(context : Context, recyclerViewLayout: RecyclerViewLayout){
            val SP = PreferenceManager.getDefaultSharedPreferences(context)
            SP.edit().putString(context.getString(R.string.pref_rec_view_layout), recyclerViewLayout.name).apply()
        }
    }
}