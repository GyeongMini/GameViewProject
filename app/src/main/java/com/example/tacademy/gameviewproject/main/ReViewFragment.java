package com.example.tacademy.gameviewproject.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacademy.gameviewproject.R;

/**
 * Created by Tacademy on 2017-02-01
 */

public class ReViewFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_review,container,false);
        return view;
    }
}
