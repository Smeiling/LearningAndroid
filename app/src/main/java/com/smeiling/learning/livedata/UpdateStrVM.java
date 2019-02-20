package com.smeiling.learning.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.smeiling.learning.Logg;

/**
 * @Author: Smeiling
 * @Date: 2019-02-20 16-17
 * @Description:
 */
public class UpdateStrVM extends ViewModel {

    private MutableLiveData<String> mCurrentStr;

    public MutableLiveData<String> getCurrentStr() {
        if (mCurrentStr == null) {
            mCurrentStr = new MutableLiveData<>();
        }
        return mCurrentStr;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Logg.debug("UpdateStrVM onCleared()");
    }
}
