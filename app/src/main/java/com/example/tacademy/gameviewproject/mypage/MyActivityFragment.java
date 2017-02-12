package com.example.tacademy.gameviewproject.mypage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacademy.gameviewproject.R;

/**
 * Created by Tacademy on 2017-02-12.
 */

public class MyActivityFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypage_myactivity_fragment,container,false);
        return view;
    }
}
