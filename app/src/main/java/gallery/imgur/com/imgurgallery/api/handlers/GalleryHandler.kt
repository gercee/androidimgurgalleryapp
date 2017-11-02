package gallery.imgur.com.imgurgallery.api.handlers

import gallery.imgur.com.imgurgallery.api.RestApi
import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem
import gallery.imgur.com.imgurgallery.api.models.ImgurResponse
import io.reactivex.Observable

/**
 * Created by GAAN on 26.10.2017.
 */
class GalleryHandler (private val api : RestApi = RestApi())
{
    fun getGalleries(section : String,
                     sort : String,
                     window : String,
                     showViral : Boolean) : Observable<List<ImgurGalleryItem>>{
        return Observable.create {
            subscriber ->
            val callResponse = api.getGalleries(section, sort, window, showViral)
            val response = callResponse.execute()
            val responseObj : ImgurResponse = response.body() as ImgurResponse;
            if (response.isSuccessful) {
                subscriber.onNext(responseObj.data)
                subscriber.onComplete()

            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}