package br.unb.meau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CadastroDoAnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_do_animal);
        getSupportActionBar().setTitle("Cadastro do animal");
    }
}
