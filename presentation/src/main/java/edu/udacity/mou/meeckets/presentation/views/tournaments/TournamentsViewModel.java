package edu.udacity.mou.meeckets.presentation.views.tournaments;

import edu.udacity.mou.meeckets.presentation.views.MeecketsViewModel;

/**
 * Created by mou on 11/21/17.
 */

public class TournamentsViewModel extends MeecketsViewModel<TournamentsPresenter> {
    @Override
    protected String getTag() {
        return TournamentsViewModel.class.getSimpleName();
    }
}
