package crossover.home.mvp.practice.taru.leastcostpathgrigwrap;

import android.app.Application;

/**
 * Created by taru on 1/30/2017.
 */

public class GridApp extends Application {

    private GridAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerGridAppComponent.builder().gridAppModule(new GridAppModule(this)).build();
    }

    public GridAppComponent getComponent() {
        return component;
    }
}
