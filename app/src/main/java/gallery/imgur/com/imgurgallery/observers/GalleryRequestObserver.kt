package gallery.imgur.com.imgurgallery.observers

import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem
import gallery.imgur.com.imgurgallery.ui.main.MainView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by GAAN on 02.11.2017.
 */
class GalleryRequestObserver(val view: MainView) : Observer<List<ImgurGalleryItem>> {

    override fun onSubscribe(d: Disposable) {
        view.showProgress()
        view.subscriveDisposable(d)
    }

    override fun onNext(t: List<ImgurGalleryItem>) {
        view.setItems(t)
        view.hideProgress()
    }

    override fun onError(e: Throwable) {
        view.hideProgress()
        view.showMessage(e.message.toString())
    }

    override fun onComplete() {
       view.hideProgress()
    }
}