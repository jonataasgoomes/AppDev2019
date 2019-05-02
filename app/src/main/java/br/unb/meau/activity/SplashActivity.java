package br.unb.meau.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

import br.unb.meau.R;
import br.unb.meau.helper.ConfigFirebase;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth = ConfigFirebase.getFirebaseAuth();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (auth.getCurrentUser() == null) {
                    startActivity(new Intent(getBaseContext(), LoginActivity.class));
                    finish();

                }else {
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    finish();
                }

            }
        },2000);
    }

}
