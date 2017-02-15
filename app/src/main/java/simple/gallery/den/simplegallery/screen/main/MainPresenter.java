package simple.gallery.den.simplegallery.screen.main;

import javax.inject.Inject;

import simple.gallery.den.simplegallery.screen.common.Presenter;
import simple.gallery.den.simplegallery.screen.net.PhotoApi;


public class MainPresenter implements Presenter<MainView> {

    private MainView mainView;
    private PhotoApi photoApi;

    @Inject
    public MainPresenter(PhotoApi photoApi) {
        this.photoApi = photoApi;
    }

    @Override
    public void attachView(MainView view) {
        mainView = view;
    }

    @Override
    public void detachView() {
        mainView = null;
    }
}
