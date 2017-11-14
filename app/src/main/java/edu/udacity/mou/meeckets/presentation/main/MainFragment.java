package edu.udacity.mou.meeckets.presentation.main;

import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import edu.udacity.mou.meeckets.R;
import edu.udacity.mou.meeckets.presentation.MeecketsFragment;

/**
 * Created by mou on 11/11/17.
 */

public class MainFragment extends MeecketsFragment<MainFragmentPresenter, MainViewModel> {
    @BindView(R.id.token_text)
    TextView tokenView;

    @Inject
    MainFragmentPresenter presenter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected void init(View view) {
        getViewModel().token().observe(this, this::showToken);
    }

    @Override
    public int layout() {
        return R.layout.fragment_main;
    }

    @Override
    protected Class<MainViewModel> viewModel() {
        return MainViewModel.class;
    }

    @Override
    protected MainFragmentPresenter getPresenter() {
        return presenter;
    }

    private void showToken(String token) {
        tokenView.setText(token);
    }

    @OnClick(R.id.login_button)
    protected void onLoginClicked(View view) {
        presenter.onLoginClick();
    }
}
