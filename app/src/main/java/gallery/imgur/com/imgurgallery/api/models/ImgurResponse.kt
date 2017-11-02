package gallery.imgur.com.imgurgallery.api.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

/**
 * Created by GAAN on 26.10.2017.
 */

class ImgurResponse (
        val data : MutableList<ImgurGalleryItem>,
        val success : Boolean,
        val status : Int?
)





