package crossover.home.mvp.practice.taru.leastcostpathgrigwrap;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by taru on 1/30/2017.
 */
@Module
public class GridAppModule {
    Application app;
    public GridAppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }
}