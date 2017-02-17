package simple.gallery.den.simplegallery.screen.net;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import simple.gallery.den.simplegallery.screen.model.Page;

/**
 * API to 500px.com. So far it has single method for fetching photos page-by-page
 */

public interface PhotoApi {

    @GET("v1/photos")
    Observable<Page> getPhotos(@Query("feature") String feature,
                               @Query("consumer_key") String consumerKey,
                               @Query("page") Integer pageNumber);
}
