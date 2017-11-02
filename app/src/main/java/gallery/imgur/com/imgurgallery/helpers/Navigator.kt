package gallery.imgur.com.imgurgallery.helpers

import android.content.Context
import android.content.Intent
import gallery.imgur.com.imgurgallery.ui.about.AboutActivity
import gallery.imgur.com.imgurgallery.ui.imageview.ImageViewActivity
import gallery.imgur.com.imgurgallery.ui.settings.SettingsActivity
import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem
import android.support.v4.content.ContextCompat.startActivity
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri


/**
 * Created by GAAN on 31.10.2017.
 */
class Navigator (){
    companion object {
        val EXTRA_GALLERY_ITEM = Navigator::class.java.name + ".EXTRA_GALLERY_ITEM"
        fun navigateToAboutActivity(context: Context){
            val intent = Intent(context, AboutActivity::class.java);
            context.startActivity(intent)
        }

        fun navigateToImageViewActivity(context: Context, item : ImgurGalleryItem){
            val intent = Intent(context, ImageViewActivity::class.java);
            intent.putExtra(EXTRA_GALLERY_ITEM, item)
            context.startActivity(intent)
        }

        fun navigateToSettingsActivity(context: Context){
            val intent = Intent(context, SettingsActivity::class.java);
            context.startActivity(intent)
        }

        fun navigateToMyLinkedinProfile(context: Context){
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://gercho"))
            val packageManager = context.getPackageManager()
            val list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            if (list.isEmpty()) {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/in/gercho"))
            }
            context.startActivity(intent)
        }
    }
}