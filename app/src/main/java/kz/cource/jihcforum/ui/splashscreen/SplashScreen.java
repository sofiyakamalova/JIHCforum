package kz.cource.jihcforum.ui.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import kz.cource.jihcforum.MainActivity;
import kz.cource.jihcforum.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen. this, kz.cource.jihcforum.ui.authentification.LoginActivity.class));
                finish();
            }
        }, 3000);

    }

}