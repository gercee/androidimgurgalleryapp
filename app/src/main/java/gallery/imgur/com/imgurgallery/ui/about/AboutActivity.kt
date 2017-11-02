package gallery.imgur.com.imgurgallery.ui.about

import android.os.Bundle
import android.text.Html
import gallery.imgur.com.imgurgallery.BuildConfig
import gallery.imgur.com.imgurgallery.ui.BaseActivity
import gallery.imgur.com.imgurgallery.R
import kotlinx.android.synthetic.main.activity_about.*
import android.text.Spanned
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import gallery.imgur.com.imgurgallery.helpers.Navigator


class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val authorLink = getString(R.string.author_name)
        val finalString = getString(R.string.about_screen_info, BuildConfig.VERSION_NAME, authorLink)
        val click = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Navigator.navigateToMyLinkedinProfile(this@AboutActivity)
            }
        }
        val startIndex = finalString.indexOf(authorLink);
        val endIndex = startIndex + authorLink.length;
        val spannableStringBuilder = SpannableStringBuilder(finalString)
        spannableStringBuilder.setSpan(click, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        aboutInfo.movementMethod = LinkMovementMethod.getInstance()
        aboutInfo.setText(spannableStringBuilder)
    }
}
