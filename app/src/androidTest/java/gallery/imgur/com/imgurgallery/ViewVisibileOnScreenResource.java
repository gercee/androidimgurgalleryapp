package gallery.imgur.com.imgurgallery;

import android.app.Activity;
import android.support.test.espresso.IdlingResource;
import android.view.View;


/**
 * Created by lagaan on 02-11-2017.
 */

public class ViewVisibileOnScreenResource implements IdlingResource {

    private Activity mActivity;
    private int mId;
    private ResourceCallback mCallback;
    private View mView;

    public ViewVisibileOnScreenResource(Activity activity, int id) {
        this.mId = id;
        this.mActivity = activity;
        mView = mActivity.findViewById(mId);
    }

    @Override
    public String getName() {
        return "Activity View Idling Resource";
    }

    @Override
    public boolean isIdleNow() {
        mCallback.onTransitionToIdle();
        return mView!=null && mView.getVisibility() == View.VISIBLE;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.mCallback = callback;
    }
}
