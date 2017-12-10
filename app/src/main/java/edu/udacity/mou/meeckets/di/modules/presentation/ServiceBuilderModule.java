package edu.udacity.mou.meeckets.di.modules.presentation;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.udacity.mou.meeckets.di.modules.presentation.services.WidgetServiceModule;
import edu.udacity.mou.meeckets.presentation.homescreenwidgets.SubscriptionsListWidgetService;

/**
 * Created by mou on 12/10/17.
 */
@Module
public interface ServiceBuilderModule {
    @ContributesAndroidInjector(modules = WidgetServiceModule.class)
    SubscriptionsListWidgetService bindHomeScreenWidget();
}
