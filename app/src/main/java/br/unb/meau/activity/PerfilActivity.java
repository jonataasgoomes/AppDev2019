package br.unb.meau.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;

import br.unb.meau.R;
import br.unb.meau.helper.UserFirebase;

public class PerfilActivity extends AppCompatActivity {

    private Button btnEditarPerfil;
    private TextView campoNomePerfil, campoNomeCompletoPerfil, campoIdadePerfil,
            campoEmailPerfil, campoLocPerfil, campoEndPerfil, campoTelPerfil,
            campoNomeUsuarioPerfil, campoHistPerfil;

    private ImageView imagePerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Meu Perfil");
        toolbar.setBackgroundResource(R.color.colorAzul);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //inicializa os campos
        initCampos();
        FirebaseUser usuarioPerfil = UserFirebase.getUsuarioAtual();


        //<<<<<<-----Carrega dados do usuário na view---->>>>>

        if (!(UserFirebase.getUsuarioAtual() == null)) {
            campoNomeCompletoPerfil.setText(usuarioPerfil.getDisplayName());
            campoEmailPerfil.setText(usuarioPerfil.getEmail());
            Uri url = usuarioPerfil.getPhotoUrl();
            if (url != null) {
                Glide.with(PerfilActivity.this)
                        .load(url)
                        .into(imagePerfil);
            } else {
                imagePerfil.setImageResource(R.drawable.user);
            }
        }

        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editPerfil = new Intent(getApplicationContext(), EditPerfilActivity.class);
                startActivity(editPerfil);
            }
        });

    }

    public void initCampos() {
        imagePerfil = findViewById(R.id.imagePerfil);
        campoNomeUsuarioPerfil = findViewById(R.id.textNomeDeUsuárioPerfil);
        campoNomePerfil = findViewById(R.id.textNomePerfil);
        campoNomeCompletoPerfil = findViewById(R.id.textNomeCompletoPerfil);
        campoIdadePerfil = findViewById(R.id.textIdadePerfil);
        campoEmailPerfil = findViewById(R.id.textEmailPerfil);
        campoLocPerfil = findViewById(R.id.textLocalizaçãoPerfil);
        campoEndPerfil = findViewById(R.id.textEnderecoPerfil);
        campoTelPerfil = findViewById(R.id.textTelefonePerfil);
        campoHistPerfil = findViewById(R.id.textHistoricoPerfil);
        btnEditarPerfil = findViewById(R.id.buttonEditarPerfil);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
