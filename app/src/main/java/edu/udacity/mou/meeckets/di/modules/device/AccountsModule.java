package edu.udacity.mou.meeckets.di.modules.device;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.R;
import edu.udacity.mou.meeckets.device.accounts.MeecketsAccountManager;
import edu.udacity.mou.meeckets.domain.device.IMeecketsAccountManager;

/**
 * Created by mou on 11/21/17.
 */

@Module
public class AccountsModule {
    @Provides
    @Named("accountType")
    public String provideAccountType(Context context) {
        return context.getString(R.string.account_type);
    }

    @Provides
    @Named("accountName")
    public String provideAccountName(Context context) {
        return context.getString(R.string.account_name);
    }

    @Provides
    @Named("tokenName")
    public String provideTokenName(Context context) {
        return context.getString(R.string.token_name);
    }

    @Provides
    public IMeecketsAccountManager provideMeecketsAccountManager(Context context,
                                                                 @Named("accountType") String accountType,
                                                                 @Named("accountName") String accountName,
                                                                 @Named("tokenName") String tokenName) {
        return new MeecketsAccountManager(context, accountType, accountName, tokenName);
    }
}
