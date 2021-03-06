package br.unb.meau.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import br.unb.meau.R;
import br.unb.meau.adapter.CardAnimalAdapter;
import br.unb.meau.helper.UserFirebase;
import br.unb.meau.model.Animal;
import br.unb.meau.model.Usuario;

public class MeusPetsActivity extends AppCompatActivity {
    private String TAG;
    private Usuario usuarioLogado;
    private FirebaseFirestore db;
    private RecyclerView recyclerAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_pets);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Meus Pets");
        toolbar.setBackgroundResource(R.color.colorAmarelo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Configurar RecyclerView
        db = FirebaseFirestore.getInstance();


        //Definindo layout do Animal
        recuperarAnimalUsuario();


    }

    public void recuperarAnimalUsuario() {
        usuarioLogado = UserFirebase.getAuthDadosUsuarioLogado();
        db.collection("animals")
                .whereEqualTo("dono","/users/"+usuarioLogado.getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Animal> cardsAnimal = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Animal animal = document.toObject(Animal.class);
                                animal.setId(document.getId());
                                cardsAnimal.add(animal);
                            }
                            recyclerAnimal = findViewById(R.id.recyclerAnimal);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerAnimal.setLayoutManager(layoutManager);
                            CardAnimalAdapter adapter = new CardAnimalAdapter(cardsAnimal, getApplicationContext());
                            recyclerAnimal.setAdapter(adapter);

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

}

