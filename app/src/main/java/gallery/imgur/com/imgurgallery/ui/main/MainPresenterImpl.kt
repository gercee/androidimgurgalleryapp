package gallery.imgur.com.imgurgallery.ui.main

import android.view.View
import gallery.imgur.com.imgurgallery.api.handlers.GalleryHandler
import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem
import gallery.imgur.com.imgurgallery.helpers.Navigator
import gallery.imgur.com.imgurgallery.observers.GalleryRequestObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by GAAN on 02.11.2017.
 */
class MainPresenterImpl(var mMainView: MainView) : MainPresenter {


    private val mGalleryHandler by lazy { GalleryHandler() }

    override fun onResume() {
        mMainView.showProgress()
    }

    override fun onDestroy() {

    }

    override fun onItemClicked(galleryItem: ImgurGalleryItem) {
        mMainView.showImageView(galleryItem)
    }

    override fun getGalleryItems(mSection: String, mSort: String, mWindow: String, mShowViral: Boolean) {
        mGalleryHandler.getGalleries(mSection,mSort,mWindow,mShowViral)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (GalleryRequestObserver(mMainView))
    }
}