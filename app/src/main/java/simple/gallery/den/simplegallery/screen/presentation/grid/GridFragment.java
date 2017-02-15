package simple.gallery.den.simplegallery.screen.presentation.grid;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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

    @BindView(R.id.photo_grid)
    RecyclerView photoRecyclerView;
    @BindView(R.id.check_request_btn)
    Button fetchButton;

    @Inject
    GridPresenter presenter;

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void fillGrid(Page page) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        photoRecyclerView.setLayoutManager(layoutManager);
        GridAdapter adapter = new GridAdapter(page.getPhotos(), getActivity());
        photoRecyclerView.setAdapter(adapter);
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
