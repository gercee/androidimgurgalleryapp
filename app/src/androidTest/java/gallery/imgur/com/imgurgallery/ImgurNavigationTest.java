package gallery.imgur.com.imgurgallery;

/**
 * Created by lagaan on 02-11-2017.
 */

import android.app.Activity;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.core.internal.deps.guava.collect.Iterables;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gallery.imgur.com.imgurgallery.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ImgurNavigationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
    }

    @Test
    public void navigateTroughAllActivities() {
        onView(withId(R.id.navigation_about)).perform(click());
        Espresso.pressBack();
        onView(withId(R.id.navigation_settings)).perform(click());
        Espresso.pressBack();
        onView(withId(R.id.fab_expand_menu_button)).perform(click());

        onView(withId(R.id.fab_staggered_grid)).perform(click());
        onView(withId(R.id.fab_expand_menu_button)).perform(click());
        onView(withId(R.id.fab_grid)).perform(click());
        onView(withId(R.id.fab_expand_menu_button)).perform(click());
        onView(withId(R.id.fab_list)).perform(click());
    }

    private void clickWhenViewVisible(int id){
        ViewVisibileOnScreenResource idlingResource = new ViewVisibileOnScreenResource(mActivityRule.getActivity(), id);
        IdlingRegistry.getInstance().register(idlingResource);
        onView(withId(id)).perform(click());
        IdlingRegistry.getInstance().unregister(idlingResource);
    }

}
