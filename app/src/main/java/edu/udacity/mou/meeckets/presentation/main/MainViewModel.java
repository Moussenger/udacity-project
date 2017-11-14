package edu.udacity.mou.meeckets.presentation.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by mou on 11/13/17.
 */

public class MainViewModel extends ViewModel {
    private MutableLiveData<String> token = new MutableLiveData<>();

    public MainViewModel() {
        token.setValue("< No token >");
    }

    public MutableLiveData<String> token() {
        return token;
    }

    public MainViewModel token(String newToken) {
        token.setValue(newToken);
        return this;
    }
}
