package br.unb.meau.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import br.unb.meau.R;
import br.unb.meau.adapter.InteressadoAdapter;
import br.unb.meau.model.Animal;
import br.unb.meau.model.Usuario;

public class InteressadosActivity extends AppCompatActivity {
    private Animal animalSelecionado;
    private RecyclerView recyclerInteressado;
    private FirebaseFirestore db;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interessados);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Interessados");
        toolbar.setBackgroundResource(R.color.colorAmarelo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //recuper dados do animal
        Bundle bundle  = getIntent().getExtras();
        if (bundle != null){
            animalSelecionado =(Animal) bundle.getSerializable( "animalSelecionado");
          }

        db = FirebaseFirestore.getInstance();

        recuperarinteressados();




    }

    public void recuperarinteressados() {
        db.collection("transactions").whereEqualTo("pet", animalSelecionado.getId())
                .whereEqualTo("finished", 0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            final List<Usuario> usuarios = new ArrayList<>();
                            final int sizex = task.getResult().size();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                final QueryDocumentSnapshot doc1 = document;
                                DocumentReference docRef = db.document("/users/" + document.getData().get("from"));
                                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                Usuario user = document.toObject(Usuario.class);
                                                user.setTransId(doc1.getId());
                                                usuarios.add(user);
                                                if (usuarios.size() == sizex) {
                                                    recyclerInteressado = findViewById(R.id.recyclerInteressado);
                                                    RecyclerView.LayoutManager layoutManager;
                                                    layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                                                    recyclerInteressado.setLayoutManager(layoutManager);
                                                    InteressadoAdapter adapter = new InteressadoAdapter(usuarios,
                                                            getApplicationContext());
                                                    recyclerInteressado.setAdapter(adapter);
                                                }
                                            } else {
                                                Log.d(TAG, "No such document");
                                            }
                                        } else {
                                            Log.d(TAG, "get failed with ", task.getException());
                                        }
                                    }
                                });
                            }
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
}
