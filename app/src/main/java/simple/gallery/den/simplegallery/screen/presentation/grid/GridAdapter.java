package simple.gallery.den.simplegallery.screen.presentation.grid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import simple.gallery.den.simplegallery.R;
import simple.gallery.den.simplegallery.screen.model.Page;
import simple.gallery.den.simplegallery.screen.model.Photo;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int TYPE_PAGE = 0;
    public final int TYPE_LOAD = 1;
    private Context context;
    private List<Photo> photoList = new ArrayList<>();
    private List<Page> pageList = new ArrayList<>();
    private OnLoadMoreListener loadMoreListener;
    private boolean isLoading;

    public GridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_PAGE) {
            return new PageHolder(inflater.inflate(R.layout.layout_cell, parent, false));
        } else {
            return new LoadHolder(inflater.inflate(R.layout.layout_loading, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= getItemCount() - 1 && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if (getItemViewType(position) == TYPE_PAGE) {
            ((PageHolder) holder).bindData(photoList.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!TextUtils.isEmpty(photoList.get(position).getName())) {
            return TYPE_PAGE;
        } else {
            return TYPE_LOAD;
        }
    }

    @Override
    public int getItemCount() {
        return photoList != null ? photoList.size() : 0;
    }

    class PageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView image;
        @BindView(R.id.title)
        TextView title;

        PageHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            //view.setOnClickListener(itemClickListener);
        }

        void bindData(Photo photo) {
            Picasso.with(context).load(photo.getPhotoUrl()).into(image);
            title.setText(photo.getName());
        }
    }

    class LoadHolder extends RecyclerView.ViewHolder {

        LoadHolder(View itemView) {
            super(itemView);
        }
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public void swapData(Page page) {
        pageList.add(page);
        photoList.addAll(pageList.get(pageList.size() - 1).getPhotos());
        notifyDataSetChanged();
        isLoading = false;
    }
}
