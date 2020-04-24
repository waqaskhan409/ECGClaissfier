package com.mlclassifier.ecgclaissfier.ui.analytics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnalyticsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AnalyticsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Analytics fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}