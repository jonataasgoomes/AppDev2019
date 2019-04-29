package br.unb.meau.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import br.unb.meau.R;
import br.unb.meau.model.Animal;

public class AnimalPerfilActivity extends AppCompatActivity {

    private Animal animalSelecionado;
    TextView nome, sexo, porte, idade, localizacao;
    ImageView imgAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_perfil);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundResource(R.color.colorAmarelo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgAnimal = findViewById(R.id.fotosPerfilAnimal);
        nome = findViewById(R.id.nomeAnimal);
        sexo = findViewById(R.id.textSexo);
        porte = findViewById(R.id.textPorte);
        idade = findViewById(R.id.textIdade);
        localizacao = findViewById(R.id.textLocalizacao);

        //recuper dados do animal
        Bundle bundle  = getIntent().getExtras();
        if (bundle != null){
            animalSelecionado =(Animal) bundle.getSerializable( "animalSelecionado");
            getSupportActionBar().setTitle(animalSelecionado.getNome());
            nome.setText(animalSelecionado.getNome());
            sexo.setText(animalSelecionado.getEspecie());
            porte.setText(animalSelecionado.getPorte());
            idade.setText(animalSelecionado.getIdade());
            localizacao.setText(animalSelecionado.getLocalizacao());


        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
