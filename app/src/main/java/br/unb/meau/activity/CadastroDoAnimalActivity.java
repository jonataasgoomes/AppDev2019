package br.unb.meau.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.unb.meau.R;

public class CadastroDoAnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_do_animal);
        // Coloca o nome na acton bar
        //getSupportActionBar().setTitle("Cadastro do animal");
    }
}
