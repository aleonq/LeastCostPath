package crossover.home.mvp.practice.taru.leastcostpathgrigwrap.mvp.views;

import crossover.home.mvp.practice.taru.leastcostpathgrigwrap.mvp.MainActivityMvp;
import crossover.home.mvp.practice.taru.leastcostpathgrigwrap.mvp.presenters.GridPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by taru on 1/30/2017.
 */
@Module
public class MainActivityModule {
    @Provides
    MainActivityMvp.Presenter provideMainActivityPresenter() {
        return new GridPresenter();
    }
}
