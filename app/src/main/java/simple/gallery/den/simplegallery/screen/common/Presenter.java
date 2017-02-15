package simple.gallery.den.simplegallery.screen.common;

/**
 * Created by Denis on 15-Feb-17.
 */

public interface Presenter<V> {
    void attachView(V view);
    void detachView();
}
