package simple.gallery.den.simplegallery.screen.presentation.detail;

import javax.inject.Inject;

import simple.gallery.den.simplegallery.screen.main.BaseMainPresenter;


public class DetailPresenter extends BaseMainPresenter<DetailView> {

    private DetailView detailView;

    @Inject
    public DetailPresenter() {
    }

    @Override
    public void onStart() {
        getView().showPhoto();
    }

    @Override
    public void onStop() {

    }
}
