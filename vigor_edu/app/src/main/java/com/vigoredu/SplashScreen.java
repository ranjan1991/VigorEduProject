package com.vigoredu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vigoredu.preferences.Pref;
import com.vigoredu.utils.Constants;


public class SplashScreen extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        moveIntent();

    }

    private void moveIntent() {
        handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent;
            if (Pref.instanceOf().getEmployID() != 0)
                intent = new Intent(SplashScreen.this, MainActivity.class);
            else
                intent = new Intent(SplashScreen.this, MainActivity.class);

            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
            finish();
        }, Constants.SPLASH_TIMEOUT);
    }

}
