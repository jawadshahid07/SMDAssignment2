package com.example.smdassignment2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int MAIN_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "SplashScreenActivity onCreate called");
        setContentView(R.layout.activity_splash_screen);

        ImageView logo = findViewById(R.id.ivLogo);
        TextView appName = findViewById(R.id.tvAppName);
        Animation splashAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        logo.startAnimation(splashAnimation);
        appName.startAnimation(splashAnimation);

        // Delay for 5 seconds before navigating to MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, ToDoListActivity.class);
                startActivityForResult(intent, MAIN_ACTIVITY_REQUEST_CODE);
            }
        }, 5000); // 5000ms = 5 seconds
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "SplashScreenActivity onActivityResult called");
        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE) {
            finish();
        }
    }
}
