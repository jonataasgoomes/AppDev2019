package br.unb.meau.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import br.unb.meau.R;
import br.unb.meau.helper.UserFirebase;
import br.unb.meau.model.Usuario;

public class PerfilActivity extends AppCompatActivity {
    private Button btnEditarPerfil, btnAceitar;
    private TextView campoNomePerfil, campoNomeCompletoPerfil, campoIdadePerfil,
            campoEmailPerfil, campoLocPerfil, campoEndPerfil, campoTelPerfil,
            campoNomeUsuarioPerfil, campoHistPerfil;

    private ImageView imagePerfil;
    private Usuario usuarioLogado,usuarioSelecionado;
    private FirebaseFirestore dataBaseRef;
    private DocumentReference userRef;
    private String TAG = "Error";

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
        //inicializa os campos e configurações iniciais
        initCampos();
        usuarioLogado = UserFirebase.getAuthDadosUsuarioLogado();
        dataBaseRef = FirebaseFirestore.getInstance();
        userRef = dataBaseRef.collection("users").document(usuarioLogado.getId());



        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editPerfil = new Intent(getApplicationContext(), EditPerfilActivity.class);
                startActivity(editPerfil);
            }
        });
        btnAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Notificaremos o usuario que a solicitação foi aceita",
                        Toast.LENGTH_LONG).show();

                Toast.makeText(getApplicationContext(), usuarioSelecionado.getTransId(),
                        Toast.LENGTH_LONG).show();
                Map objeto = new HashMap();
                objeto.put("finished", 1);
                FirebaseFirestore dbRef = FirebaseFirestore.getInstance();
                DocumentReference userRef = dbRef.collection("transactions").document(usuarioSelecionado.getTransId());
                userRef.update(objeto);

                finish();

            }
        });

    }

    private void recuperarFoto() {
        usuarioLogado = UserFirebase.getAuthDadosUsuarioLogado();
        campoNomeCompletoPerfil.setText(usuarioLogado.getNome());
        campoEmailPerfil.setText(usuarioLogado.getEmail());
        String caminhoFoto = usuarioLogado.getPicPath();
        if (caminhoFoto != null) {
            Uri url = Uri.parse(caminhoFoto);
            Glide.with(PerfilActivity.this)
                    .load(caminhoFoto)
                    .into(imagePerfil);
        } else {
            imagePerfil.setImageResource(R.drawable.user);
        }


    }

    private void recuperarDadosUsuarioLogado() {
        usuarioLogado = UserFirebase.getAuthDadosUsuarioLogado();
        userRef = dataBaseRef.collection("users").document(usuarioLogado.getId());
        userRef.addSnapshotListener(this,
                new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        if (documentSnapshot.exists()) {
                            Usuario user = documentSnapshot.toObject(Usuario.class);
                            String cidade = user.getCidade();
                            String estado = user.getEstado();
                            String localizacao = cidade + " - " + estado;
                            campoIdadePerfil.setText(user.getIdade());
                            campoNomeUsuarioPerfil.setText(user.getUsuario());
                            campoLocPerfil.setText(localizacao);
                            campoEndPerfil.setText(user.getEndereco());
                            campoTelPerfil.setText(user.getTelefone());
                            campoNomePerfil.setText(user.getNome());
                        } else if (e != null) {
                            Log.w(TAG, "Got an exception");
                        }
                    }
                }
        );


    }

    private void recuperarDadosUsuario(){
        Bundle bundle  = getIntent().getExtras();
        if (bundle != null){
            usuarioSelecionado =(Usuario) bundle.getSerializable( "usuarioSelecionado");
            getSupportActionBar().setTitle(usuarioSelecionado.getNome());

            Uri uriFotoAnimal = Uri.parse(usuarioSelecionado.getPicPath());
            Glide.with(this).load(uriFotoAnimal).into(imagePerfil);

            campoNomePerfil.setText(usuarioSelecionado.getNome());
            campoIdadePerfil.setText(usuarioSelecionado.getIdade());
            campoEmailPerfil.setText(usuarioSelecionado.getEmail());
            campoLocPerfil.setText(usuarioSelecionado.getLocal());
            campoTelPerfil.setText(usuarioSelecionado.getTelefone());
            campoNomeUsuarioPerfil.setText(usuarioSelecionado.getUsuario());

        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        usuarioLogado = UserFirebase.getAuthDadosUsuarioLogado();
        Bundle bundle  = getIntent().getExtras();
        if (bundle != null){
            usuarioSelecionado =(Usuario) bundle.getSerializable( "perfilSelecionado");
            getSupportActionBar().setTitle(usuarioSelecionado.getNome());

            Uri uriFotoAnimal = Uri.parse(usuarioSelecionado.getPicPath());
            Glide.with(this).load(uriFotoAnimal).into(imagePerfil);
            campoNomeCompletoPerfil.setText(usuarioSelecionado.getNome());
            campoNomePerfil.setText(usuarioSelecionado.getNome());
            campoIdadePerfil.setText(usuarioSelecionado.getIdade());
            campoEmailPerfil.setText(usuarioSelecionado.getEmail());
            campoLocPerfil.setText(usuarioSelecionado.getCidade());
            campoTelPerfil.setText(usuarioSelecionado.getTelefone());
            campoNomeUsuarioPerfil.setText(usuarioSelecionado.getUsuario());
            btnAceitar.setVisibility(View.VISIBLE);
            campoHistPerfil.setVisibility(View.GONE);

        }else{
            recuperarDadosUsuarioLogado();
            recuperarFoto();
            btnEditarPerfil.setVisibility(View.VISIBLE);
            campoEndPerfil.setVisibility(View.VISIBLE);
        }

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
        btnAceitar = findViewById(R.id.buttonAceitar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
