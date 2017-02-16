package simple.gallery.den.simplegallery.screen.presentation.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import simple.gallery.den.simplegallery.R;
import simple.gallery.den.simplegallery.screen.common.BasePresenter;
import simple.gallery.den.simplegallery.screen.common.Layout;
import simple.gallery.den.simplegallery.screen.main.BaseMainFragment;
import simple.gallery.den.simplegallery.screen.model.Photo;

import static simple.gallery.den.simplegallery.screen.common.Constants.PHOTO_URL;

/**
 * Detail fragment for showing single photo
 */

@Layout(id = R.layout.fragment_details)
public class DetailFragment extends BaseMainFragment implements DetailView {

    @BindView(R.id.image_full)
    ImageView fullImage;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.camera)
    TextView camera;
    @BindView(R.id.share_btn)
    FloatingActionButton shareBtn;
    @Inject
    DetailPresenter presenter;
    private Photo photo;

    public static DetailFragment newInstance(Photo photo) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(PHOTO_URL, photo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        photo = (Photo) getArguments().getSerializable(PHOTO_URL);
        if (photo != null) {
            Picasso.with(getActivity()).load(photo.getPhotoUrl()).into(fullImage);
            title.setText(getString(R.string.title, checkEmptyName(photo.getName())));
            author.setText(getString(R.string.author, checkEmptyName(photo.getUser().getUserName())));
            camera.setText(getString(R.string.camera, checkEmptyName(photo.getCamera())));
        } else {
            Picasso.with(getActivity()).load(android.R.drawable.ic_menu_gallery).into(fullImage);
        }
    }

    private String checkEmptyName(String name) {
        return TextUtils.isEmpty(name) ? getString(R.string.no_name) : name;
    }

    @Override
    public void showPhoto() {
        //TODO: Implement photo scrolling
    }

    @OnClick(R.id.share_btn)
    public void onShareClick(View v) {
        presenter.sharePhoto(fullImage);
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject() {
        getMainActivityComponent().inject(this);
    }
}
