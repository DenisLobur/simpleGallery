package simple.gallery.den.simplegallery.screen.net;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface PhotoApi {

    @GET("v1/photos")
    Observable<Object> getWeatherByCityname(@Query("feature") String feature,
                                               @Query("consumer_key") String consumer_key);
}
