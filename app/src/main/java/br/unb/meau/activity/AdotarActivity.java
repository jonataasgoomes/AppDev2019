package br.unb.meau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import br.unb.meau.R;
import br.unb.meau.adapter.CardAnimalAdapter;
import br.unb.meau.helper.RecyclerItemClickListener;
import br.unb.meau.model.Animal;

public class AdotarActivity extends AppCompatActivity {
    private RecyclerView recyclerAnimal;
    private List<Animal> cardsAnimais = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adotar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Adotar");
        toolbar.setBackgroundResource(R.color.colorAmarelo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Configurar RecyclerView
        recyclerAnimal = findViewById(R.id.recyclerAnimal);

        //Definindo layout do Animal
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerAnimal.setLayoutManager(layoutManager);

        //Definindo adaptador
        carregarCards();
        CardAnimalAdapter adapter = new CardAnimalAdapter(cardsAnimais, this);
        recyclerAnimal.setAdapter(adapter);

        recyclerAnimal.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                recyclerAnimal, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Animal cardSelecionado = cardsAnimais.get(position);
                Intent cardPerfil = new Intent(getApplicationContext(), AnimalPerfilActivity.class);
                cardPerfil.putExtra("animalSelecionado", cardSelecionado);
                startActivity(cardPerfil);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

    }

    public void carregarCards() {
        Animal animal = new Animal(
                "RAGNAR",
                "FILHOTE",
                "MACHO",
                "ASA SUL - DF",
                "GRANDE",
                R.drawable.cachorro1);
        this.cardsAnimais.add(animal);

        animal = new Animal(
                "LOLLA",
                "ADULTO",
                "FEMÊA",
                "ASA NORTE - DF",
                "MEDIO",
                R.drawable.cachorro2);
        this.cardsAnimais.add(animal);

        animal = new Animal(
                "CHOKYTO",
                "FILHOTE",
                "MACHO",
                "SOBRADINHO - DF",
                "PEQUENO",
                R.drawable.cachorro3);
        this.cardsAnimais.add(animal);

        animal = new Animal(
                "OLIVER",
                "ADULTO",
                "MACHO",
                "TAGUATINGA -DF",
                "GRANDE",
                R.drawable.cachorro4);
        this.cardsAnimais.add(animal);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
