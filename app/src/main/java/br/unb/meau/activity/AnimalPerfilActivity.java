package br.unb.meau.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import br.unb.meau.R;
import br.unb.meau.helper.UserFirebase;
import br.unb.meau.model.Animal;
import br.unb.meau.model.Usuario;

public class AnimalPerfilActivity extends AppCompatActivity {

    private Animal animalSelecionado;
    TextView nome, sexo, porte, idade, localizacao, castrado, vermifugado,
    vacinado,doencas, temperamento,exigencias,sobre;
    ImageView imgAnimal;
    private Button btnAdotar, btnInteressados, btnRemover;
    private Usuario usuarioLogado;


    LinearLayout linearViewMeusPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_perfil);
        usuarioLogado = UserFirebase.getAuthDadosUsuarioLogado();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundResource(R.color.colorAmarelo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        linearViewMeusPets = findViewById(R.id.meusPetsLayout);

        imgAnimal = findViewById(R.id.fotosPerfilAnimal);
        nome = findViewById(R.id.nomeAnimal);
        sexo = findViewById(R.id.textSexo);
        porte = findViewById(R.id.textPorte);
        idade = findViewById(R.id.textIdade);
        localizacao = findViewById(R.id.textLocalizacao);
        castrado = findViewById(R.id.textCastrado);
        vermifugado = findViewById(R.id.textVermifugado);
        vacinado = findViewById(R.id.textVacinado);
        doencas = findViewById(R.id.textDoencas);
        temperamento = findViewById(R.id.textTemperamento);
        exigencias = findViewById(R.id.textExigencias);
        sobre = findViewById(R.id.textSobre);
        btnAdotar = findViewById(R.id.buttonAdotar);
        btnInteressados = findViewById(R.id.buttonInteressados);
        btnRemover = findViewById(R.id.buttonRemover);

        //recuper dados do animal
        Bundle bundle  = getIntent().getExtras();
        if (bundle != null){
            animalSelecionado =(Animal) bundle.getSerializable( "animalSelecionado");
            getSupportActionBar().setTitle(animalSelecionado.getNome());

            Uri uriFotoAnimal = Uri.parse(animalSelecionado.getImagem());
            Glide.with(this).load(uriFotoAnimal).into(imgAnimal);

            nome.setText(animalSelecionado.getNome());
            sexo.setText(animalSelecionado.getSexo());
            porte.setText(animalSelecionado.getPorte());
            idade.setText(animalSelecionado.getIdade());
            localizacao.setText(animalSelecionado.getLocalizacao());
            sobre.setText(animalSelecionado.getSobre());

            if(animalSelecionado.getDono().equals("/users/"+usuarioLogado.getId())){
                linearViewMeusPets.setVisibility(View.VISIBLE);

            }else{
                btnAdotar.setVisibility(View.VISIBLE);

            }

        }

        btnAdotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, Object> interestedMap = new HashMap<>();
                interestedMap.put("pet", animalSelecionado.getId());
                interestedMap.put("from", usuarioLogado.getId());
                interestedMap.put("to", animalSelecionado.getDono());
                interestedMap.put("finished", 0);
                interestedMap.put("type", "adopt");

                FirebaseFirestore dbRef = FirebaseFirestore.getInstance();
                dbRef.collection("transactions").add(interestedMap);

                Toast.makeText(getApplicationContext(), "Adicionado", Toast.LENGTH_LONG).show();
            }
        });

        btnInteressados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent interessados = new Intent(getApplicationContext(),InteressadosActivity.class);
                interessados.putExtra("animalSelecionado", animalSelecionado);
                startActivity(interessados);
            }
        });

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
