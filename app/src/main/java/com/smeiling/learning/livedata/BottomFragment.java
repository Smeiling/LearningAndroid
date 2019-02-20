package com.smeiling.learning.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smeiling.learning.R;

/**
 * @Author: Smeiling
 * @Date: 2019-02-20 17-15
 * @Description:
 */
public class BottomFragment extends Fragment {
    private AccountVM mModel;
    private TextView mText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mText = view.findViewById(R.id.fragment_text_view);
        mModel = ViewModelProviders.of(getActivity()).get(AccountVM.class);
        mModel.getAccount().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(@Nullable AccountBean accountBean) {
                mText.setText(accountBean.toString());
            }
        });
    }
}
