package simple.gallery.den.simplegallery.screen.inject.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import simple.gallery.den.simplegallery.screen.inject.scopes.ApplicationScope;
import simple.gallery.den.simplegallery.screen.net.PhotoApi;

@ApplicationScope
@Module(includes = NetworkModule.class)
public class RestApiModule {
    private static final String BASE_URL = "https://api.500px.com/";

    @Provides
    @ApplicationScope
    public PhotoApi getPhotoApi(Retrofit retrofit) {
        return retrofit.create(PhotoApi.class);
    }

    @Provides
    @ApplicationScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }
}
