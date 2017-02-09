package com.example.tacademy.gameviewproject.main.review;

import android.app.Application;

import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;

/**
 *  2017-02-09 카메라 등록
 */

public class CameraApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        RxPaparazzo.register(this);
    }
}
