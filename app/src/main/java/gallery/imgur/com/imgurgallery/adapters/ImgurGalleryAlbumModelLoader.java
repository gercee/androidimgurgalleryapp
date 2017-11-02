package gallery.imgur.com.imgurgallery.adapters;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;


import java.io.InputStream;

import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem;

/**
 * Created by GAAN on 30.10.2017.
 */

public class ImgurGalleryAlbumModelLoader extends BaseGlideUrlLoader<ImgurGalleryItem> {

    public static class Factory implements ModelLoaderFactory<ImgurGalleryItem, InputStream> {
        private final ModelCache<ImgurGalleryItem, GlideUrl> modelCache = new ModelCache<ImgurGalleryItem, GlideUrl>(500);

        @Override
        public ModelLoader<ImgurGalleryItem, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new ImgurGalleryAlbumModelLoader(multiFactory.build(GlideUrl.class, InputStream.class),
                    modelCache);
        }

        @Override
        public void teardown() {
        }
    }

    public ImgurGalleryAlbumModelLoader(ModelLoader<GlideUrl, InputStream> urlLoader,
                                        ModelCache<ImgurGalleryItem, GlideUrl> modelCache) {
        super(urlLoader, modelCache);
    }

    @Override
    public boolean handles(ImgurGalleryItem model) {
        return true;
    }

    @Override
    protected String getUrl(ImgurGalleryItem model, int width, int height, Options options) {
        return model.getImageUrl();
    }
}
