package gallery.imgur.com.imgurgallery.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import gallery.imgur.com.imgurgallery.R;
import gallery.imgur.com.imgurgallery.adapters.GalleryItemClick;

/**
 * Created by GAAN on 26.10.2017.
 */

public class GalleryItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView description;
    public ImageView image;
    private GalleryItemClick mOnGalleryItemClick;

    public GalleryItemViewHolder(View itemView, GalleryItemClick onGalleryItemClick) {
        super(itemView);
        itemView.setOnClickListener(this);
        description = itemView.findViewById(R.id.gallery_item_description);
        image = itemView.findViewById(R.id.gallery_item_image);
        mOnGalleryItemClick = onGalleryItemClick;
    }

    @Override
    public void onClick(View view) {
        mOnGalleryItemClick.onGalleryItemClick(getAdapterPosition());
    }
}
