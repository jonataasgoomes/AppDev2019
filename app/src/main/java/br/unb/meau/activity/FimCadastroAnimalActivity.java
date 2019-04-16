package br.unb.meau.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.unb.meau.R;

public class FimCadastroAnimalActivity extends AppCompatActivity {


    private Button meusPetsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_cadastro_animal);
        // Remove a action bar
        //Objects.requireNonNull(getSupportActionBar()).hide();

        meusPetsBtn = findViewById(R.id.buttonPets);

        meusPetsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
