package gallery.imgur.com.imgurgallery.api.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by lagaan on 02-11-2017.
 */
data class ImgurGalleryItem(
        val id: String?,
        val title: String?,
        val description: String?,
        val datetime: Int?,
        val views: Int?,
        val vote: String?,
        val favorite: Boolean,
        val comment_count: Int?,
        val topic: String?,
        val topic_id: Int?,
        val link: String?,
        val ups: Int?,
        val downs: Int?,
        val points: Int?,
        val score: Int?,
        val is_album: Boolean?,
        val in_most_viral: Boolean?,
        val cover: String?,
        val cover_width: Int?,
        val cover_height: Int?,
        val privacy: String?,
        val layout: String?,
        val images_count: Int?,
        val images: List<ImgurImage>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.createTypedArrayList(ImgurImage)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeValue(datetime)
        parcel.writeValue(views)
        parcel.writeString(vote)
        parcel.writeByte(if (favorite) 1 else 0)
        parcel.writeValue(comment_count)
        parcel.writeString(topic)
        parcel.writeValue(topic_id)
        parcel.writeString(link)
        parcel.writeValue(ups)
        parcel.writeValue(downs)
        parcel.writeValue(points)
        parcel.writeValue(score)
        parcel.writeValue(is_album)
        parcel.writeValue(in_most_viral)
        parcel.writeString(cover)
        parcel.writeValue(cover_width)
        parcel.writeValue(cover_height)
        parcel.writeString(privacy)
        parcel.writeString(layout)
        parcel.writeValue(images_count)
        parcel.writeTypedList(images)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImgurGalleryItem> {
        override fun createFromParcel(parcel: Parcel): ImgurGalleryItem {
            return ImgurGalleryItem(parcel)
        }

        override fun newArray(size: Int): Array<ImgurGalleryItem?> {
            return arrayOfNulls(size)
        }
    }

    fun getImageUrl() : String? {
        if(this.is_album == true){
            if(this.images?.size?.toInt()?.compareTo(0)!=0){
                return this.images?.get(0)?.link
            }
        }
        return link
    }
}