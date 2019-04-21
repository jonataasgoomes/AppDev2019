package br.unb.meau.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import br.unb.meau.R;

public class SemCadastroActivity extends AppCompatActivity {

    Button btnFazerCadastro, btnFazerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_cadastro);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastro");
        toolbar.setBackgroundResource(R.color.colorAzul);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        btnFazerCadastro = findViewById(R.id.buttonFazerCadastro);
        btnFazerLogin = findViewById(R.id.buttonFazerLogin);

        btnFazerCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastro = new Intent(getApplicationContext(), CadastroPessoaActivity.class);
                startActivity(cadastro);
            }
        });

        btnFazerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}



