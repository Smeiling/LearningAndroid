package com.smeiling.learning.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @Author: Smeiling
 * @Date: 2019-02-20 17-14
 * @Description:
 */
public class AccountVM extends ViewModel {

    private MutableLiveData<AccountBean> account;

    public MutableLiveData<AccountBean> getAccount() {
        if (account == null) {
            account = new MutableLiveData<>();
        }
        return account;
    }
}
