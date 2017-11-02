package gallery.imgur.com.imgurgallery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem;
import gallery.imgur.com.imgurgallery.observers.GalleryRequestObserver;
import gallery.imgur.com.imgurgallery.ui.main.MainView;
import io.reactivex.disposables.Disposable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by lagaan on 02-11-2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class GalleryRequestObserverTest {
    private GalleryRequestObserver mSut;

    @Mock
    MainView mMockMainView;

    @Mock
    Disposable mMockDisposable;

    @Mock
    List<ImgurGalleryItem>  mMockListItems;

    @Mock
    Throwable mMockThrowable;

    @Before
    public void setUp(){
        mSut = new GalleryRequestObserver(mMockMainView);
        when(mMockThrowable.getMessage()).thenReturn("error_message");
    }

    @Test
    public void testOnSubscribe(){

        mSut.onSubscribe(mMockDisposable);
        verify(mMockMainView, times(1)).showProgress();
        verify(mMockMainView, times(1)).subscriveDisposable(mMockDisposable);
    }

    @Test
    public void testOnNext(){

        mSut.onNext(mMockListItems);
        verify(mMockMainView, times(1)).setItems(mMockListItems);
        verify(mMockMainView, times(1)).hideProgress();
    }

    @Test
    public void testOnError(){

        mSut.onError(mMockThrowable);
        verify(mMockMainView, times(1)).showMessage(mMockThrowable.getMessage().toString());
        verify(mMockMainView, times(1)).hideProgress();
    }

    @Test
    public void testOnComplete(){

        mSut.onComplete();
        verify(mMockMainView, times(1)).hideProgress();
    }
}
