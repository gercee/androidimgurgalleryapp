package gallery.imgur.com.imgurgallery.api

import gallery.imgur.com.imgurgallery.api.models.ImgurResponse
import gallery.imgur.com.imgurgallery.helpers.Utils
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by GAAN on 26.10.2017.
 */
interface ImgurApi {
    @GET("gallery/{section}/{sort}/{window}")
    @Headers(Utils.authorization)
    fun getGalleries(@Path(value = "section", encoded = true) section : String,
                     @Path(value = "sort", encoded = true) sort : String,
                     @Path(value = "window", encoded = true) window : String,
                     @Query("showViral") showViral : Boolean) : Call<ImgurResponse>
}