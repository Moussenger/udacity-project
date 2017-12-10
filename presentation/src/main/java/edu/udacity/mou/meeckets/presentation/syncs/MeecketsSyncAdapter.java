package edu.udacity.mou.meeckets.presentation.syncs;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetTournaments;

/**
 * Created by mou on 12/10/17.
 */

public class MeecketsSyncAdapter extends AbstractThreadedSyncAdapter {
    private GetTournaments getTournaments;

    @Inject
    public MeecketsSyncAdapter(Context context, boolean autoInitialize, GetTournaments getTournaments) {
        super(context, autoInitialize);
        this.getTournaments = getTournaments;
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        //Now this request do nothing. When backend is available, this request will update cache from server
        getTournaments.run(null).subscribe();
        Log.d("WORKS?", "OLE!");
    }
}
