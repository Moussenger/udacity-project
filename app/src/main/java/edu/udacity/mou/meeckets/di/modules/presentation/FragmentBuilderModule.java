package edu.udacity.mou.meeckets.di.modules.presentation;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.udacity.mou.meeckets.di.modules.presentation.fragments.MainFragmentModule;
import edu.udacity.mou.meeckets.presentation.main.MainFragment;

/**
 * Created by mou on 11/13/17.
 */

@Module
public interface FragmentBuilderModule {
    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    MainFragment bindMainFragment();
}
