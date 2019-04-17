package br.unb.meau.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.unb.meau.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    private Button btnLogin;
    private Button btnAdotar;
    private Button btnAjudar;
    private Button btnCadastroDoAnimal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //configurar toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        drawerLayout = findViewById(R.id.main_actity);
        NavigationView navigationView = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar ,
                R.string.drawer_open
                ,R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        btnLogin = findViewById(R.id.buttonLogin);
        btnAdotar = findViewById(R.id.buttonPets);
        btnAjudar = findViewById(R.id.buttonAjudar);
        btnCadastroDoAnimal = findViewById(R.id.buttonCadastrarAnimal);


        //Ações dos botões na view - abrem Activitys conrrepondentes.
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(this, "Item selecionado", Toast.LENGTH_SHORT).show();
        closeDrawer();
        return true;
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }
    private void openDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
        }
        super.onBackPressed();
    }
}
