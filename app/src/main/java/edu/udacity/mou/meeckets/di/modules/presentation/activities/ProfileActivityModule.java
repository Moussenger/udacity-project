package edu.udacity.mou.meeckets.di.modules.presentation.activities;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogout;
import edu.udacity.mou.meeckets.domain.interactors.auth.GetUser;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetSubscriptions;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetTournament;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.presentation.views.profile.ProfileActivity;
import edu.udacity.mou.meeckets.presentation.views.profile.ProfilePresenter;
import edu.udacity.mou.meeckets.presentation.views.profile.adapters.SubscriptionsAdapter;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class ProfileActivityModule {

    @Provides
    ProfilePresenter provideProfilePresenter(DoLogout doLogout, GetSubscriptions getSubscriptions,
                                             GetUser getUser, CheckLogin checkLogin, GetTournament getTournament) {
        return new ProfilePresenter(doLogout, getSubscriptions, checkLogin, getUser, getTournament);
    }

    @Provides
    SubscriptionsAdapter provideSubscriptionsAdapter(ProfileActivity context, StorageMapper<Subscription> subscriptionStorageMapper) {
        return new SubscriptionsAdapter(context, subscriptionStorageMapper);
    }
}
