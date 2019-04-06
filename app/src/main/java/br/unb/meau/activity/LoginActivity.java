package br.unb.meau.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.unb.meau.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
    }
}
