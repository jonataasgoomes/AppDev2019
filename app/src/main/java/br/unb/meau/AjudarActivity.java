package br.unb.meau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AjudarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajudar);
        getSupportActionBar().setTitle("Ajudar");
    }
}
