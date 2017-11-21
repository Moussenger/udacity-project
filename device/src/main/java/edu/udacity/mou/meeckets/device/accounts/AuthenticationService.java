package edu.udacity.mou.meeckets.device.accounts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by mou on 11/21/17.
 */

public class AuthenticationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        AccountAuthenticator authenticator = new AccountAuthenticator(this);
        return authenticator.getIBinder();
    }
}
