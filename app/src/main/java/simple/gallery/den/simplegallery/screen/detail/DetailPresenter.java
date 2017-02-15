package simple.gallery.den.simplegallery.screen.detail;

import simple.gallery.den.simplegallery.screen.common.Presenter;


public class DetailPresenter implements Presenter<DetailView> {

    private DetailView detailView;

    @Override
    public void attachView(DetailView view) {
        detailView = view;
    }

    @Override
    public void detachView() {
        detailView = null;
    }
}
