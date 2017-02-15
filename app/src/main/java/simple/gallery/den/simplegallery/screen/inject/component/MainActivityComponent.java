package simple.gallery.den.simplegallery.screen.inject.component;

import dagger.Component;
import simple.gallery.den.simplegallery.screen.presentation.detail.DetailFragment;
import simple.gallery.den.simplegallery.screen.inject.modules.ContextModule;
import simple.gallery.den.simplegallery.screen.inject.modules.RestApiModule;
import simple.gallery.den.simplegallery.screen.inject.scopes.ApplicationScope;
import simple.gallery.den.simplegallery.screen.presentation.grid.GridFragment;

@ApplicationScope
@Component(modules = {ContextModule.class, RestApiModule.class})
public interface MainActivityComponent {
    void inject(GridFragment gridFragment);

    void inject(DetailFragment detailFragment);
}
