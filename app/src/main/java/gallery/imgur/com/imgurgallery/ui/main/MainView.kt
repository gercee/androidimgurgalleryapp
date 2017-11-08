package gallery.imgur.com.imgurgallery.ui.main

import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem
import gallery.imgur.com.imgurgallery.ui.View
import io.reactivex.disposables.Disposable

/**
 * Created by GAAN on 02.11.2017.
 */
interface MainView : View {
    fun showProgress()

    fun hideProgress()

    fun setItems(items: List<ImgurGalleryItem>)

    fun showImageView(item : ImgurGalleryItem)

    fun subscriveDisposable(d: Disposable)

}