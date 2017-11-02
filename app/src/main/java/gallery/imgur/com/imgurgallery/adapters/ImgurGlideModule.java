package gallery.imgur.com.imgurgallery.adapters;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem;

/**
 * Created by GAAN on 27.10.2017.
 */

@GlideModule
public final class ImgurGlideModule extends AppGlideModule {
    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.append(ImgurGalleryItem.class, InputStream.class, new ImgurGalleryAlbumModelLoader.Factory());
    }

    // Disable manifest parsing to avoid adding similar modules twice.
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
