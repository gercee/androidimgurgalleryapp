package gallery.imgur.com.imgurgallery.api

import gallery.imgur.com.imgurgallery.api.models.ImgurResponse
import gallery.imgur.com.imgurgallery.helpers.Utils
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by GAAN on 26.10.2017.
 */

class RestApi {

    private val restApi : ImgurApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Utils.baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        restApi = retrofit.create(ImgurApi::class.java)
    }

    fun getGalleries(section : String,
                     sort : String,
                     window : String,
                     showViral : Boolean) : Call<ImgurResponse>{
        return restApi.getGalleries(section, sort, window, showViral);
    }
}