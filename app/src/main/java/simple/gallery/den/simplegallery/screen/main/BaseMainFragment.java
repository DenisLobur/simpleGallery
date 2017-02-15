package simple.gallery.den.simplegallery.screen.main;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.Toast;

import simple.gallery.den.simplegallery.screen.common.BaseFragment;
import simple.gallery.den.simplegallery.screen.common.MainActivity;
import simple.gallery.den.simplegallery.screen.inject.component.MainActivityComponent;

public abstract class BaseMainFragment extends BaseFragment implements BaseMainView {

    @Override
    public void showError(@StringRes int message) {
        Toast.makeText(getContext(), getString(message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        MainActivity mainActivity = (MainActivity) getActivity();
        //noinspection unchecked
        getPresenter().setRouter(mainActivity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //noinspection unchecked
        getPresenter().setRouter(null);
    }

    protected MainActivityComponent getMainActivityComponent() {
        return ((MainActivity)getActivity()).getMainActivityComponent();
    }
}
