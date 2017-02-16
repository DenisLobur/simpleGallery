package simple.gallery.den.simplegallery.screen.common;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;


import simple.gallery.den.simplegallery.R;
import simple.gallery.den.simplegallery.screen.inject.component.DaggerMainActivityComponent;
import simple.gallery.den.simplegallery.screen.inject.component.MainActivityComponent;
import simple.gallery.den.simplegallery.screen.inject.modules.ContextModule;
import simple.gallery.den.simplegallery.screen.inject.modules.NetworkModule;
import simple.gallery.den.simplegallery.screen.inject.modules.RestApiModule;
import simple.gallery.den.simplegallery.screen.main.BaseMainFragment;
import simple.gallery.den.simplegallery.screen.model.Page;
import simple.gallery.den.simplegallery.screen.model.Photo;
import simple.gallery.den.simplegallery.screen.presentation.detail.DetailFragment;
import simple.gallery.den.simplegallery.screen.presentation.grid.GridFragment;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainRouter {

    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setSupportActionBar(toolbar);
        mainActivityComponent =
                DaggerMainActivityComponent
                        .builder()
                        .networkModule(new NetworkModule())
                        .restApiModule(new RestApiModule())
                        .contextModule(new ContextModule(this))
                        .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            addBackStack(new GridFragment());
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void addBackStack(BaseMainFragment fragment) {
//        Preconditions.checkNotNull(fragment);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content, fragment);
        tx.addToBackStack(fragment.getFragmentName());
        tx.commit();
    }

    @Override
    public void showDetails(Photo photo) {
        addBackStack(DetailFragment.newInstance(photo));
    }

    public MainActivityComponent getMainActivityComponent() {
        return mainActivityComponent;
    }
}
