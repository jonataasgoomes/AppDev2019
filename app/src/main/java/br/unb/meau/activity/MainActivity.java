package br.unb.meau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

import br.unb.meau.R;
import br.unb.meau.activity.AdotarActivity;
import br.unb.meau.activity.AjudarActivity;
import br.unb.meau.activity.CadastroDoAnimalActivity;
import br.unb.meau.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnAdotar;
    private Button btnAjudar;
    private Button btnCadastroDoAnimal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Remove a action bar
        //Objects.requireNonNull(getSupportActionBar()).hide();

        btnLogin = findViewById(R.id.buttonLogin);
        btnAdotar = findViewById(R.id.buttonAdotar);
        btnAjudar = findViewById(R.id.buttonAjudar);
        btnCadastroDoAnimal = findViewById(R.id.buttonCadastrarAnimal);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        btnAdotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdotarActivity.class);
                startActivity(intent);
            }
        });
        btnAjudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AjudarActivity.class);
                startActivity(intent);
            }
        });
        btnCadastroDoAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroDoAnimalActivity.class);
                startActivity(intent);
            }
        });
    }
}
