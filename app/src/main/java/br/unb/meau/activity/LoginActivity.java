package br.unb.meau.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.unb.meau.R;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Logar usuário
        usuario.signInWithEmailAndPassword("jonataasgoomes@gmail.com","jr12345")
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Log.i("signIn", "Sucesso ao logar usuario");
                            }
                            else{
                                Log.i("signIn", "Erro ao logar usuario");
                            }
                        }
                    });*/

        /*Deslogar usuário
        usuario.signOut();*/

        /*Verificar usuario logado
        if (usuario.getCurrentUser() != null){
            Log.i("CreateUser", "Usuário logado!");
        }else
            Log.i("CreateUser", "Usuário deslogado");*/

        /*Cadastro de um usuario
        usuario.createUserWithEmailAndPassword("jonataasgoomes@gmail.com","jr12345")
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Log.i("CreateUser", "Sucesso ao cadastrar");
                                }
                                else{
                                    Log.i("CreateUser", "Erro ao cadastrar usuario");
                                }
                            }
                        });*/

    }
}
