package br.unb.meau.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.unb.meau.R;
import br.unb.meau.helper.ConfigFirebase;
import br.unb.meau.helper.UserFirebase;
import br.unb.meau.model.Usuario;

public class CadastroPessoaActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoIdade;
    private EditText campoEmail;
    private EditText campoEstado;
    private EditText campoCidade;
    private EditText campoEndereco;
    private EditText campoTelefone;
    private EditText campoUsuario;
    private EditText campoSenha;
    private EditText campoSenhaConf;
    private Button btnCadastrar;
    private static final int SELECTION_GALERY = 200;
    private AlertDialog dialog;
    private Usuario usuario;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        //Inicializa e personaliza a toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastro Pessoal");
        toolbar.setBackgroundResource(R.color.colorBtnLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Inicializar campos
        initCampos();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textNome = campoNome.getText().toString();
                String Idade = campoIdade.getText().toString();
                String textEmail = campoEmail.getText().toString();
                String textEstado = campoEstado.getText().toString();
                String textCidade = campoCidade.getText().toString();
                String textEndereco = campoEndereco.getText().toString();
                String textTelefone = campoTelefone.getText().toString();
                String textUsuario = campoUsuario.getText().toString();
                String textSenha = campoSenha.getText().toString();
                String textSenhaConf = campoSenhaConf.getText().toString();


                if (!textNome.isEmpty()) {
                    if (!textUsuario.isEmpty()) {
                        if (!textEmail.isEmpty()) {
                            if (!textSenha.isEmpty()) {
                                if (!textSenhaConf.isEmpty()) {
                                    if (textSenha.equals(textSenhaConf)) {


                                        usuario = new Usuario();
                                        usuario.setNome(textNome);
                                        usuario.setIdade((Idade));
                                        usuario.setEmail(textEmail);
                                        usuario.setEstado(textEstado);
                                        usuario.setCidade(textCidade);
                                        usuario.setEndereco(textEndereco);
                                        usuario.setTelefone(textTelefone);
                                        usuario.setUsuario(textUsuario);
                                        usuario.setSenha(textSenha);
                                        cadastrar(usuario);


                                    } else {
                                        Toast.makeText(CadastroPessoaActivity.this,
                                                "Senhas diferentes",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(CadastroPessoaActivity.this,
                                            "Confirme a Senha",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(CadastroPessoaActivity.this,
                                        "Preencha a Senha",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(CadastroPessoaActivity.this,
                                    "Preencha o E-mail",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroPessoaActivity.this,
                                "Preencha o Nome de usuário",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroPessoaActivity.this,
                            "Preencha o nome",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    //Método responsável por cadastrar o usuário
    public void cadastrar(final Usuario usuario) {
        carregamentoDialog("Cadastrando usuário");
        auth = ConfigFirebase.getFirebaseAuth();
        auth.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            try {
                                //Salvar dados do usuario no firebase
                                String idUsuario = task.getResult().getUser().getUid();
                                usuario.setId(idUsuario);
                                usuario.salvar();

                                //salvar dados no profile do firebase
                                UserFirebase.attNomeUsuario(usuario.getNome());
                                dialog.cancel();
                                Toast.makeText(CadastroPessoaActivity.this,
                                        "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), AdicionarFotoActivity.class));
                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            String error = "";
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                dialog.cancel();
                                error = "Digite uma senha mais forte!";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                dialog.cancel();
                                error = "Digite um e-mail válido";
                            } catch (FirebaseAuthUserCollisionException e) {
                                dialog.cancel();
                                error = "email já cadastrado";
                            } catch (Exception e) {
                                dialog.cancel();
                                error = "ao cadastrar usuário:" + e.getMessage();
                                e.printStackTrace();
                            }
                            dialog.cancel();
                            Toast.makeText(CadastroPessoaActivity.this,
                                    "Erro: " + error, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


    }

    //Metodo para iniciar os componentes
    public void initCampos() {
        campoNome = findViewById(R.id.editTextNome);
        campoIdade = findViewById(R.id.editTextIdade);
        campoEmail = findViewById(R.id.editTextEmail);
        campoEstado = findViewById(R.id.editTextEstado);
        campoCidade = findViewById(R.id.editTextCidade);
        campoEndereco = findViewById(R.id.editTextEndereco);
        campoTelefone = findViewById(R.id.editTextTelefone);
        campoUsuario = findViewById(R.id.editTextNomeUsuario);
        campoSenha = findViewById(R.id.editTextPass);
        campoSenhaConf = findViewById(R.id.editTextPassConf);
        btnCadastrar = findViewById(R.id.buttonFazerCadastro);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    private void carregamentoDialog(String titulo) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setCancelable(false);
        alert.setView(R.layout.carregamento);
        dialog = alert.create();
        dialog.show();

    }


}
