package gallery.imgur.com.imgurgallery.ui

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription

/**
 * Created by GAAN on 26.10.2017.
 */
open class BaseActivity : AppCompatActivity(){
    protected var compositeDisposable = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        compositeDisposable = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item)
    }
}