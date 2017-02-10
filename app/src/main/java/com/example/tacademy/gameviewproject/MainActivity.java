package com.example.tacademy.gameviewproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tacademy.gameviewproject.intro.IntroActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
        finish();

//        overridePendingTransition(0, 0);

    }
//    public void onNextPage(View view){
//        Intent intent = new Intent(this, MainFragmentActivity.class);
//        startActivity(intent);
//        finish();
//    }

}
