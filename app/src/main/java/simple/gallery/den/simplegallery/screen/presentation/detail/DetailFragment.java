package simple.gallery.den.simplegallery.screen.presentation.detail;

import android.support.annotation.NonNull;

import simple.gallery.den.simplegallery.R;
import simple.gallery.den.simplegallery.screen.common.BasePresenter;
import simple.gallery.den.simplegallery.screen.common.Layout;
import simple.gallery.den.simplegallery.screen.main.BaseMainFragment;

@Layout(id = R.layout.fragment_details)
public class DetailFragment extends BaseMainFragment implements DetailView {

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void inject() {

    }
}
