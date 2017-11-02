package gallery.imgur.com.imgurgallery.ui.imageview

import kotlinx.android.synthetic.main.activity_image_view.*
import android.os.Bundle
import android.view.View
import gallery.imgur.com.imgurgallery.R
import gallery.imgur.com.imgurgallery.adapters.GlideApp
import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem
import gallery.imgur.com.imgurgallery.helpers.Navigator
import gallery.imgur.com.imgurgallery.ui.BaseActivity

class ImageViewActivity : BaseActivity() {

    lateinit var item : ImgurGalleryItem;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        item = intent.getParcelableExtra(Navigator.EXTRA_GALLERY_ITEM);
        GlideApp.with(this).load(item.getImageUrl()).into(image)
        txtTitle.setText(item.title)
        if(item.description?.length?.compareTo(0)!=0) {
            txtDescription.setText(item.description)
        }else{
            txtDescription.visibility = View.GONE
        }
        txtUpwotes.setText(getString(R.string.upvotes, item.ups.toString()))
        txtDownvotes.setText(getString(R.string.downvotes, item.downs.toString()))
        txtScore.setText(getString(R.string.score, item.score.toString()))
    }
}
