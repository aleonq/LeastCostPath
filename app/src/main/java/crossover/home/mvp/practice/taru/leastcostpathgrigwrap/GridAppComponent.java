package crossover.home.mvp.practice.taru.leastcostpathgrigwrap;

import crossover.home.mvp.practice.taru.leastcostpathgrigwrap.mvp.views.MainActivity;
import crossover.home.mvp.practice.taru.leastcostpathgrigwrap.mvp.views.MainActivityModule;
import dagger.Component;

/**
 * Created by taru on 1/30/2017.
 */
@Component(modules = {GridAppModule.class, MainActivityModule.class})
public interface GridAppComponent {
    void inject(MainActivity activity);
}
