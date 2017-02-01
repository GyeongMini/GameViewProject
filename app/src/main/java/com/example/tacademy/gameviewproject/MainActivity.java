package com.example.tacademy.gameviewproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tacademy.gameviewproject.main.MainFragmentActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNextPage(View view){
        Intent intent = new Intent(this, MainFragmentActivity.class);
        startActivity(intent);
        finish();
    }
}
