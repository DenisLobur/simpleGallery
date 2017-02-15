package simple.gallery.den.simplegallery.screen.presentation.grid;

import android.util.Log;

import java.util.concurrent.Executors;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import simple.gallery.den.simplegallery.screen.common.Constants;
import simple.gallery.den.simplegallery.screen.main.BaseMainPresenter;
import simple.gallery.den.simplegallery.screen.net.PhotoApi;


public class GridPresenter extends BaseMainPresenter<GridView> {

    private GridView gridView;
    private PhotoApi photoApi;
    private static Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

    @Inject
    public GridPresenter(PhotoApi photoApi) {
        this.photoApi = photoApi;
    }

    public void fetchPhotos() {
        photoApi.getPhotos("popular", Constants.CONSUMER_KEY, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
                .map(it -> Log.d("result", it.toString()))
                .subscribe(stringResult -> {
                    Log.d("result", stringResult.toString());
                }, throwable -> {
                    Throwable th = throwable;
                });

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
