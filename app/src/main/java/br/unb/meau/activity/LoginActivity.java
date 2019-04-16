package br.unb.meau.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import br.unb.meau.R;
import br.unb.meau.config.ConfigFirebase;
import br.unb.meau.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail, campoSenha;
    private Button btnEntrar;
    private Usuario usuario;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UsuarioLogado();


        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById( R.id.editSenha);
        btnEntrar = findViewById(R.id.buttonEntrar);

        //botão entrada para o Login.
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pegando os campos para verificar se estão vazios
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

               if (!textoEmail.isEmpty()){
                   if (!textoSenha.isEmpty()){

                       usuario = new Usuario();
                       usuario.setEmail(textoEmail);
                       usuario.setSenha(textoSenha);
                       validarLogin();

                   }else
                       Toast.makeText(LoginActivity.this, "Preencha a senha!",
                               Toast.LENGTH_SHORT).show();
               }else
                   Toast.makeText(LoginActivity.this, "Preencha o email!",
                           Toast.LENGTH_SHORT).show();


            }
        });



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


    public void btnCadastrar(View view ) {
        startActivity(new Intent(this, CadastroPessoaActivity.class));
    }

    public void validarLogin(){
        auth = ConfigFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
                ).addOnCompleteListener
                (new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful() ){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login realizado", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    String error = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        error = "Email ou senha incorretos";
                    }catch (FirebaseAuthInvalidUserException e){
                        error = "Usuário não cadastrado";
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, error,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void UsuarioLogado(){
        auth = ConfigFirebase.getFirebaseAuth();
        if (auth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
