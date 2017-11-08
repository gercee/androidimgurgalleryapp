package gallery.imgur.com.imgurgallery.ui.main

import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem

/**
 * Created by GAAN on 02.11.2017.
 */
interface MainPresenter {
    fun onResume()
    fun onDestroy()
    fun onItemClicked(galleryItem: ImgurGalleryItem)
    fun getGalleryItems(mSection: String, mSort: String, mWindow: String, mShowViral: Boolean)
}