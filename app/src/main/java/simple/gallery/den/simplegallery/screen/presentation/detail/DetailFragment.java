package simple.gallery.den.simplegallery.screen.presentation.detail;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        } else {
            presenter.sharePhoto(fullImage);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            if (getActivity() == null) {
                return;
            }
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                showError(R.string.share_error_permission);
            }
        } else {
            presenter.sharePhoto(fullImage);
        }
    }

    private void requestPermission() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
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
