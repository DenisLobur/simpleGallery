package simple.gallery.den.simplegallery.screen.presentation.grid;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import simple.gallery.den.simplegallery.screen.common.Constants;
import simple.gallery.den.simplegallery.screen.main.BaseMainPresenter;
import simple.gallery.den.simplegallery.screen.model.Page;
import simple.gallery.den.simplegallery.screen.model.Photo;
import simple.gallery.den.simplegallery.screen.net.PhotoApi;

import static simple.gallery.den.simplegallery.screen.common.Constants.FEATURE;

/**
 * Main presenter for downloading photos page-by-page
 */
public class GridPresenter extends BaseMainPresenter<GridView> {

    private PhotoApi photoApi;
    private static Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
    private List<Page> pages = new ArrayList<>();
    AtomicInteger increment = new AtomicInteger();

    @Inject
    public GridPresenter(PhotoApi photoApi) {
        this.photoApi = photoApi;
    }

    public void fetchPhotos() {
        int i = increment.incrementAndGet();
        photoApi.getPhotos(FEATURE, Constants.CONSUMER_KEY, i)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
                //.map(it -> Log.d("result", it.toString()))
                .subscribe(pageResult -> {
                    Log.d("result", pageResult.toString());
                    getView().fillGrid(pageResult);
                }, throwable -> {
                    Throwable th = throwable;
                });
    }

    @Override
    public void onStart() {
        fetchPhotos();
    }

    @Override
    public void onStop() {

    }

    public void openDetails(Photo photo) {
        getRouter().showDetails(photo);
    }
}
