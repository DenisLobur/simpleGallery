package simple.gallery.den.simplegallery.screen.inject.modules;

import dagger.Module;
import dagger.Provides;
import simple.gallery.den.simplegallery.screen.inject.scopes.ApplicationScope;
import simple.gallery.den.simplegallery.screen.main.MainPresenter;
import simple.gallery.den.simplegallery.screen.net.PhotoApi;

@Module
public class MainPresenterModule {
    @Provides
    @ApplicationScope
    MainPresenter provideMainPresenter(PhotoApi photoApi) {
        return new MainPresenter(photoApi);
    }
}
