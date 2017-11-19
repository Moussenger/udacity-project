package edu.udacity.mou.meeckets.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import edu.udacity.mou.meeckets.MeecketsApplication;
import edu.udacity.mou.meeckets.di.modules.AppModule;
import edu.udacity.mou.meeckets.di.modules.data.RepositoriesModule;
import edu.udacity.mou.meeckets.di.modules.data.datasources.DatabaseDatasourcesModule;
import edu.udacity.mou.meeckets.di.modules.data.datasources.NetworkDatasourcesModule;
import edu.udacity.mou.meeckets.di.modules.domain.InteractorsModule;
import edu.udacity.mou.meeckets.di.modules.domain.SchedulersModule;
import edu.udacity.mou.meeckets.di.modules.presentation.ActivityBuilderModule;

/**
 * Created by mou on 11/11/17.
 */

@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        SchedulersModule.class,
        InteractorsModule.class,
        NetworkDatasourcesModule.class,
        DatabaseDatasourcesModule.class,
        RepositoriesModule.class,
        ActivityBuilderModule.class,
        //FragmentBuilderModule.class
})
@Singleton
public interface MeecketsComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        MeecketsComponent build();
    }

    void inject(MeecketsApplication application);
}
