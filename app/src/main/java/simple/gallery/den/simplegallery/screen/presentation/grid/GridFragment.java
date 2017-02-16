package simple.gallery.den.simplegallery.screen.presentation.grid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import simple.gallery.den.simplegallery.R;
import simple.gallery.den.simplegallery.screen.common.BasePresenter;
import simple.gallery.den.simplegallery.screen.common.Layout;
import simple.gallery.den.simplegallery.screen.main.BaseMainFragment;
import simple.gallery.den.simplegallery.screen.model.Page;
import simple.gallery.den.simplegallery.screen.model.Photo;

/**
 * Main fragment for showing grid photos
 */

@Layout(id = R.layout.fragment_main)
public class GridFragment extends BaseMainFragment implements GridView {

    public static final String TAG = GridFragment.class.getSimpleName();

    @BindView(R.id.photo_grid)
    RecyclerView photoRecyclerView;

    @Inject
    GridPresenter presenter;

    private GridAdapter adapter;

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        photoRecyclerView.setLayoutManager(layoutManager);
        adapter = new GridAdapter(getActivity());
        adapter.setLoadMoreListener(() -> photoRecyclerView.post(() -> presenter.fetchPhotos()));
        adapter.setOnItemClickListener(photoClickListener);
        photoRecyclerView.setAdapter(adapter);
    }

    @Override
    public void fillGrid(Page page) {
        adapter.swapData(page);
    }

    @Override
    public void updateGrid() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void inject() {
        getMainActivityComponent().inject(this);
    }

    GridAdapter.OnItemClickListener photoClickListener = new GridAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Photo photo) {
            presenter.openDetails(photo);
        }
    };
}
