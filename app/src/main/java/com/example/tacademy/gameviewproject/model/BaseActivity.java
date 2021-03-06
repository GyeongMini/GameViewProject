package com.example.tacademy.gameviewproject.model;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.tacademy.gameviewproject.R;

public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 상태바 색상 ===============
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){  // LOLLIPOP = 21
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.main));
        }
        //==================================
    }

    //공통 기능 1
    // primitive(원시) 타입
    // byte, short, int, long
    // float, double
    // boolean
    // char , 언

    //프로그레스바 선언
    private ProgressDialog pd;
    //프로그레스바 처리
    //프로그레스바 보이기
    public void showProgress(String msg)
    {
        // 객체를 1회만 생성한다.
        if( pd == null ) {
            // 생선한다.
            pd = new ProgressDialog(this);
            // 백키로 닫는 기능을 제거한다.
            pd.setCancelable(false);

        }
        // 원하는 메시지를 세팅한다.
        pd.setMessage(msg);
        // 화면에 띠워라
        pd.show();
    }
    // 프로그레스바 숨기기
    public void hideProgress()
    {
        // 닫는다 : 객체가 존재하고, 보일때만
        if ( pd != null && pd.isShowing() ){
        pd.dismiss();
    }
    }

}






