package com.example.smdassignment2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int MAIN_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView logo = findViewById(R.id.logo);
        Animation splashAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        logo.startAnimation(splashAnimation);

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
        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE) {
            finish();  // Finish SplashActivity when MainActivity is done
        }
    }
}
