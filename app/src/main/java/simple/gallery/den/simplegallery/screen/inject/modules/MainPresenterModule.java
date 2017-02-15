package simple.gallery.den.simplegallery.screen.inject.modules;

import dagger.Module;
import dagger.Provides;
import simple.gallery.den.simplegallery.screen.inject.scopes.ApplicationScope;
import simple.gallery.den.simplegallery.screen.presentation.grid.GridPresenter;
import simple.gallery.den.simplegallery.screen.net.PhotoApi;

@Module
public class MainPresenterModule {
    @Provides
    @ApplicationScope
    GridPresenter provideMainPresenter(PhotoApi photoApi) {
        return new GridPresenter(photoApi);
    }
}
