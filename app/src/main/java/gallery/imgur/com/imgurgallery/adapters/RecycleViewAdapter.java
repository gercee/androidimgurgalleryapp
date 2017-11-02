package gallery.imgur.com.imgurgallery.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;

import java.util.Collections;
import java.util.List;

import gallery.imgur.com.imgurgallery.R;
import gallery.imgur.com.imgurgallery.adapters.viewholders.GalleryItemViewHolder;
import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem;
import gallery.imgur.com.imgurgallery.helpers.Navigator;
import gallery.imgur.com.imgurgallery.ui.main.MainPresenter;
import gallery.imgur.com.imgurgallery.ui.main.MainView;

/**
 * Created by GAAN on 26.10.2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<GalleryItemViewHolder> implements ListPreloader.PreloadModelProvider<ImgurGalleryItem>, GalleryItemClick  {
    private List<ImgurGalleryItem> mItemList;
    private Context mContext;
    private RecyclerViewLayout mViewLayout;
    private GlideRequest<Drawable> mFullRequest;
    private MainPresenter mMainPresenter;


    public RecycleViewAdapter(Context context, List<ImgurGalleryItem> itemList, RecyclerViewLayout viewLayout, MainPresenter mainPresenter) {
        this.mItemList = itemList;
        this.mContext = context;
        this.mViewLayout = viewLayout;
        this.mMainPresenter = mainPresenter;

    }

    public void setFullRequest(GlideRequest<Drawable> fullRequest) {
        this.mFullRequest = fullRequest;
    }

    public void setItemList(List<ImgurGalleryItem> mItemList) {
        this.mItemList = mItemList;
    }

    public RecyclerViewLayout getViewLayout() {
        return mViewLayout;
    }

    public void setViewLayout(RecyclerViewLayout mViewLayout) {
        this.mViewLayout = mViewLayout;
    }

    @Override
    public GalleryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, null);
        switch (mViewLayout){
            case GridView:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_view, null);
                break;
            case ListView:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, null);
                break;
            case StaggeredGridView:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staggered_grid_view, null);
                break;
        }
        GalleryItemViewHolder rcv = new GalleryItemViewHolder(layoutView, this);
        return rcv;
    }

    @Override
    public void onBindViewHolder(GalleryItemViewHolder holder, int position) {
        ImgurGalleryItem data = mItemList.get(position);
        mFullRequest.load(data)
                .into(holder.image);

        holder.description.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public List<ImgurGalleryItem> getItemList() {
        return mItemList;
    }


    @NonNull
    @Override
    public List<ImgurGalleryItem> getPreloadItems(int position) {
        return Collections.singletonList(getItemList().get(position));
    }

    @Nullable
    @Override
    public RequestBuilder getPreloadRequestBuilder(ImgurGalleryItem item) {
        return null;
    }

    @Override
    public void onGalleryItemClick(int position) {
        mMainPresenter.onItemClicked(getItemList().get(position));
    }
}
