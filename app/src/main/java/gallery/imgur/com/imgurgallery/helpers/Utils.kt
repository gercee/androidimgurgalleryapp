package gallery.imgur.com.imgurgallery.helpers

import android.app.Activity
import android.util.DisplayMetrics
import gallery.imgur.com.imgurgallery.adapters.RecyclerViewLayout

/**
 * Created by GAAN on 27.10.2017.
 */
class Utils(){
    companion object {
        const val baseUrl = "https://api.imgur.com/3/"
        const val authorization = "Authorization:Client-ID 6d79f78b17b0046"
        fun getDisplayMetrics(activity: Activity):DisplayMetrics {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics
        }

        fun getGalleryItemWidht(activity : Activity, viewLayout : RecyclerViewLayout) : Int{
            val displayMetrics = Utils.getDisplayMetrics(activity)
            when (viewLayout) {
                RecyclerViewLayout.GridView, RecyclerViewLayout.StaggeredGridView -> return if(displayMetrics.widthPixels > displayMetrics.heightPixels) displayMetrics.widthPixels/2 else displayMetrics.heightPixels/2
            }
            return Integer.valueOf(if(displayMetrics.widthPixels > displayMetrics.heightPixels) displayMetrics.widthPixels else displayMetrics.heightPixels)
        }

    }
}