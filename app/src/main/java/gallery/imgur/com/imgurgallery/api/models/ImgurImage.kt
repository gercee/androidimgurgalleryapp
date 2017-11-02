package gallery.imgur.com.imgurgallery.api.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by lagaan on 02-11-2017.
 */

class ImgurImage (
        val id: String?,
        val title: String?,
        val description: String?,
        val datetime: Int?,
        val type: String?,
        val animated: Boolean?,
        val width: Int?,
        val height: Int?,
        val size: Int?,
        val views: Int?,
        val name: String?,
        val section: String?,
        val vote: String?,
        val gifv: String?,
        val mp4: String?,
        val mp4_size: Int?,
        val looping: Boolean?,
        val favorite: Boolean?,
        val link: String?,
        open val comment_count: Int?,
        val in_gallery: Boolean?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeValue(datetime)
        parcel.writeString(type)
        parcel.writeValue(animated)
        parcel.writeValue(width)
        parcel.writeValue(height)
        parcel.writeValue(size)
        parcel.writeValue(views)
        parcel.writeString(name)
        parcel.writeString(section)
        parcel.writeString(vote)
        parcel.writeString(gifv)
        parcel.writeString(mp4)
        parcel.writeValue(mp4_size)
        parcel.writeValue(looping)
        parcel.writeValue(favorite)
        parcel.writeString(link)
        parcel.writeValue(comment_count)
        parcel.writeValue(in_gallery)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImgurImage> {
        override fun createFromParcel(parcel: Parcel): ImgurImage {
            return ImgurImage(parcel)
        }

        override fun newArray(size: Int): Array<ImgurImage?> {
            return arrayOfNulls(size)
        }
    }
}