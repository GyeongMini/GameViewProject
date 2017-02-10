package com.example.tacademy.gameviewproject.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.tacademy.gameviewproject.R;
import com.example.tacademy.gameviewproject.sign.SignUpActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_intro);

        // 화면 사이즈 풀로 만땅으로 만들어 버리기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);


        Log.i("AB", "로딩시작");
        Log.i("AB", "로딩진행중..");
        // 2초후에 로딩 닫고 center로 이동한다.
        handler.sendEmptyMessageDelayed(0,1000*2);
    }

    // 안드 OS중 handler로 스케줄을 검!
    Handler handler = new Handler(){ //{}adapter 방식
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                // 화면으로 이동(Intent)
                Intent intent = new Intent(IntroActivity.this, SignUpActivity.class);
                // 화면 전환 수행
                startActivity(intent);
                finish();

        }
    };
    //상속 받아서 재정의(Override) 하는것을 줄여서 위처럼 사용
//    MyHandler h = new MyHandler();
//    class MyHandler extends Handler
//    {
//
//    }
}
