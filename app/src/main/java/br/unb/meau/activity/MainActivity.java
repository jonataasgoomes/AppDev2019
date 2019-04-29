package br.unb.meau.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import br.unb.meau.R;
import br.unb.meau.helper.ConfigFirebase;
import br.unb.meau.helper.UserFirebase;
import br.unb.meau.model.Usuario;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    private Button btnLogin;
    private Button btnAdotar;
    private Button btnAjudar;
    private Button btnCadastroDoAnimal;
    private FirebaseAuth auth;
    private Usuario usuarioLogado;
    private ImageView imgMenuPic;
    private TextView textMenuNome;
    private MenuItem perfil, meusPets, favoritos, chat,sair, atalhos,info, conf,
        cadastar, adotar,ajudar,apadrinhar, dicas, eventos, legis, termo, hist,privacidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = ConfigFirebase.getFirebaseAuth();

        //configurar toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        drawerLayout = findViewById(R.id.main_activity);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.drawer_open
                , R.string.drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        btnLogin = findViewById(R.id.buttonLogin);
        btnAdotar = findViewById(R.id.buttonPets);
        btnAjudar = findViewById(R.id.buttonAjudar);
        btnCadastroDoAnimal = findViewById(R.id.buttonCadastrarAnimal);
        imgMenuPic = navigationView.getHeaderView(0).findViewById(R.id.menuImgUser);
        textMenuNome = navigationView.getHeaderView(0).findViewById(R.id.menuTextNome);



        //configurando o menu de navegação;
        Menu menu = navigationView.getMenu();

        perfil = menu.findItem(R.id.meu_perfil);
        meusPets = menu.findItem(R.id.meus_pets);
        favoritos = menu.findItem(R.id.favoritos);
        chat = menu.findItem(R.id.chat);

        atalhos = menu.findItem(R.id.atalhos);
        cadastar = menu.findItem(R.id.cadastrar_um_pet);
        adotar = menu.findItem(R.id.adotar_um_pet);
        ajudar = menu.findItem(R.id.ajudar_um_pet);
        apadrinhar = menu.findItem(R.id.apadrinhar_um_pet);

        cadastar.setVisible(false);
        adotar.setVisible(false);
        ajudar.setVisible(false);
        apadrinhar.setVisible(false);

        info = menu.findItem(R.id.informacoes);
        dicas = menu.findItem(R.id.dicas);
        eventos = menu.findItem(R.id.eventos);
        legis = menu.findItem(R.id.legislacao);
        termo = menu.findItem(R.id.termo_de_adocao);
        hist = menu.findItem(R.id.hist_de_adocao);

        dicas.setVisible(false);
        eventos.setVisible(false);
        legis.setVisible(false);
        termo.setVisible(false);
        hist.setVisible(false);

        conf = menu.findItem(R.id.conf);
        privacidade = menu.findItem(R.id.privacidade);

        privacidade.setVisible(false);


        sair = menu.findItem(R.id.menu_sair);
        atalhos.setActionView(R.layout.menu_image);
        info.setActionView(R.layout.menu_image);
        conf.setActionView(R.layout.menu_image);

        if (auth.getCurrentUser() == null) {
            textMenuNome.setText("Seja bem-vindo");
            perfil.setVisible(false);
            meusPets.setVisible(false);
            favoritos.setVisible(false);
            chat.setVisible(false);
            sair.setVisible(false);
            btnLogin.setVisibility(View.VISIBLE);
        }else {
            sair.setVisible(true);
            perfil.setVisible(true);
            meusPets.setVisible(true);
            favoritos.setVisible(true);
            chat.setVisible(true);
        }


        //Ações dos botões na view - abrem Activitys conrrepondentes.
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


    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void closeDrawer() {

        drawerLayout.closeDrawer(GravityCompat.START);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        
        switch (menuItem.getItemId()) {
            case R.id.meu_perfil:
                    Intent perfil = new Intent(this, PerfilActivity.class);
                    startActivity(perfil);
                break;
            case R.id.meus_pets:
                    Intent meusPets = new Intent(this, MeusPetsActivity.class);
                    startActivity(meusPets);
                    break;
            case R.id.favoritos:
                    Intent favoritos = new Intent(this, FavoritosActivity.class);
                    startActivity(favoritos);
                    break;

            case R.id.atalhos:
                cadastar.setVisible(true);
                adotar.setVisible(true);
                ajudar.setVisible(true);
                apadrinhar.setVisible(true);

                dicas.setVisible(false);
                eventos.setVisible(false);
                legis.setVisible(false);
                termo.setVisible(false);
                hist.setVisible(false);

                privacidade.setVisible(false);

                break;

            case R.id.cadastrar_um_pet:
                Intent cadastarPet = new Intent(this, CadastroDoAnimalActivity.class);
                startActivity(cadastarPet);
                break;
            case R.id.adotar_um_pet:
                Intent adotarView = new Intent(this, AdotarActivity.class);
                startActivity(adotarView);
                break;
            case R.id.ajudar_um_pet:
                Intent ajudarView = new Intent(this, AjudarActivity.class);
                startActivity(ajudarView);
                break;
            case R.id.apadrinhar_um_pet:
                Intent apadrinharView = new Intent(this, ApadrinharActivity.class);
                startActivity(apadrinharView);
                break;
            case R.id.informacoes:
                cadastar.setVisible(false);
                adotar.setVisible(false);
                ajudar.setVisible(false);
                apadrinhar.setVisible(false);

                dicas.setVisible(true);
                eventos.setVisible(true);
                legis.setVisible(true);
                termo.setVisible(true);
                hist.setVisible(true);

                privacidade.setVisible(false);
                break;


            case R.id.termo_de_adocao:
                closeDrawer();
                Intent termoView = new Intent(this, TermoDeAdocaoActivity.class);
                startActivity(termoView);
                break;
            case R.id.conf:
                cadastar.setVisible(false);
                adotar.setVisible(false);
                ajudar.setVisible(false);
                apadrinhar.setVisible(false);

                dicas.setVisible(false);
                eventos.setVisible(false);
                legis.setVisible(false);
                termo.setVisible(false);
                hist.setVisible(false);

                privacidade.setVisible(true);
                break;
            case R.id.menu_sair:
                deslogar();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(0, 0);
                Toast.makeText(this, "Logout realizado com sucesso", Toast.LENGTH_SHORT).show();
                closeDrawer();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }

    }

    private void deslogar() {
        try {
            auth.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recuperarDados() {
        if (!(UserFirebase.getUsuarioAtual() == null)) {
            usuarioLogado = UserFirebase.getAuthDadosUsuarioLogado();
            textMenuNome.setText(usuarioLogado.getNome());
            String caminhoFoto = usuarioLogado.getPicPath();
            if (caminhoFoto != null) {
                Uri rl = Uri.parse(caminhoFoto);
                Glide.with(MainActivity.this)
                        .load(caminhoFoto)
                        .into(imgMenuPic);
            } else {
                imgMenuPic.setImageResource(R.drawable.user);
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarDados();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recuperarDados();
    }
}
