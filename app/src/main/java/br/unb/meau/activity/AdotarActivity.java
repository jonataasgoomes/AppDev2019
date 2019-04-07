package br.unb.meau.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.unb.meau.R;
import br.unb.meau.adapter.CardAnimalAdapter;
import br.unb.meau.model.Animal;

public class AdotarActivity extends AppCompatActivity {
    private RecyclerView recyclerAnimal;
    private List<Animal> cardsAnimais = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adotar);
        // Coloca o nome na acton bar
        //getSupportActionBar().setTitle("Adotar");
        // Configurar RecyclerView
        recyclerAnimal = findViewById(R.id.recyclerAnimal);
        //Definindo layout do Animal
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerAnimal.setLayoutManager(layoutManager);
        //Definindo adaptador
        carregarCards();
        CardAnimalAdapter adapter = new CardAnimalAdapter(cardsAnimais);
        recyclerAnimal.setAdapter(adapter);
    }

    public void carregarCards(){
        Animal animal = new Animal(
                    "RAGNAR",
                    "FILHOTE",
                    "MACHO",
                    "ASA SUL - DF",
                    "GRANDE",R.drawable.cachorro1);
        this.cardsAnimais.add(animal);

        animal = new Animal(
                "LOLLA",
                "ADULTO",
                "FEMÊA",
                "ASA NORTE - DF",
                "MEDIO",R.drawable.cachorro2);
        this.cardsAnimais.add(animal);

        animal = new Animal(
                "CHOKYTO",
                "FILHOTE",
                "MACHO",
                "SOBRADINHO - DF",
                "PEQUENO",R.drawable.cachorro3);
        this.cardsAnimais.add(animal);

        animal = new Animal(
                "OLIVER",
                "ADULTO",
                "MACHO",
                "TAGUATINGA -DF",
                "GRANDE",R.drawable.cachorro4);
        this.cardsAnimais.add(animal);


    }
}
