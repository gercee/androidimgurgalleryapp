package gallery.imgur.com.imgurgallery.ui.main

import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem
import gallery.imgur.com.imgurgallery.helpers.Navigator

/**
 * Created by GAAN on 02.11.2017.
 */
class MainPresenterImpl(var mMainView: MainView?) : MainPresenter {


    override fun onResume() {
        mMainView?.showProgress()
    }

    override fun onDestroy() {
        mMainView = null;
    }

    override fun onItemClicked(galleryItem: ImgurGalleryItem) {
        mMainView?.showImageView(galleryItem)
    }
}