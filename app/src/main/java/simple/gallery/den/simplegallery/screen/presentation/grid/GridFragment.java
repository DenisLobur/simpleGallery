package simple.gallery.den.simplegallery.screen.presentation.grid;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import simple.gallery.den.simplegallery.R;
import simple.gallery.den.simplegallery.screen.common.BasePresenter;
import simple.gallery.den.simplegallery.screen.common.Layout;
import simple.gallery.den.simplegallery.screen.main.BaseMainFragment;
import simple.gallery.den.simplegallery.screen.model.Page;

@Layout(id = R.layout.fragment_main)
public class GridFragment extends BaseMainFragment implements GridView {

    public static final String TAG = GridFragment.class.getName();

    @BindView(R.id.photo_grid)
    RecyclerView photoRecyclerView;
    @BindView(R.id.check_request_btn)
    Button fetchButton;

    @Inject
    GridPresenter presenter;

    private GridAdapter adapter;

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void fillGrid(List<Page> pages) {

        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        photoRecyclerView.setLayoutManager(layoutManager);
        photoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = recyclerView.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();

                Log.d(TAG, "visibleItemCount: " + visibleItemCount
                        + "\ntotal: " + totalItemCount
                        + "\n first: " + firstVisibleItem
                        + "\n last: " + lastVisibleItem);

                if (lastVisibleItem == (totalItemCount - 1)) {
                        Toast.makeText(getActivity(), "bottom", Toast.LENGTH_SHORT).show();
                        presenter.fetchPhotos(2);
                }
            }
        });
        adapter = new GridAdapter(pages, getActivity());
        //adapter.setItemClickListener(view -> presenter.openDetails(page));
        photoRecyclerView.setAdapter(adapter);
    }

    @Override
    public void updateGrid() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void inject() {
        getMainActivityComponent().inject(this);
    }

    @OnClick(R.id.check_request_btn)
    public void fetchPhotos(View view) {
        //presenter.fetchPhotos();
    }
}
