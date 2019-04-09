package com.zjf.commonlib.base.vm;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import com.zjf.commonlib.utils.TUtil;

import javax.inject.Inject;


public class BaseViewModel<T extends BaseRepository> extends ViewModel {


    public T mRepository;

    public BaseViewModel(T repository) {
        mRepository = repository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mRepository != null) {
            mRepository.unDisposable();
        }
    }

}
