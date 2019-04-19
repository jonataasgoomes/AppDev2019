package br.unb.meau.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import br.unb.meau.R;
import br.unb.meau.helper.ConfigFirebase;
import br.unb.meau.helper.UserFirebase;
import br.unb.meau.model.Usuario;

public class EditPerfilActivity extends AppCompatActivity {


    private Usuario usuarioLogado;
    private ImageView imageEditPerfil;
    private TextView textAlterarFoto;
    private TextInputEditText editNomeCompletoPerfil,
            editIdadePerfil,
            editEmailPerfil,
            editCidPerfil,
            editEndPerfil,
            editTelPerfil,
            editNomeUsuarioPerfil,
            editEstPerfil;
    private Button btnSalvarEdit;
    private static final int SELECTION_GALERY = 200;
    private StorageReference storageRef;
    private String idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);
        usuarioLogado = UserFirebase.getDadosUsuarioLogado();
        storageRef = ConfigFirebase.getFirebaseStorage();
        idUsuario = UserFirebase.getIdUser();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Editar Perfil");
        toolbar.setBackgroundResource(R.color.colorBtnLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        //iniciando componentes;
        initCampos();

        //Dados do Usuário;
        FirebaseUser usuarioPerfil = UserFirebase.getUsuarioAtual();
        if (!(UserFirebase.getUsuarioAtual() == null)) {
            editNomeCompletoPerfil.setText(usuarioPerfil.getDisplayName());
            editEmailPerfil.setText(usuarioPerfil.getEmail());
            Uri url = usuarioPerfil.getPhotoUrl();
            if(url != null){
                Glide.with(EditPerfilActivity.this)
                        .load(url)
                        .into(imageEditPerfil);
            }else {
                imageEditPerfil.setImageResource(R.drawable.user);
            }
        }


        //Salvar alterações
        btnSalvarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeAtt = editNomeCompletoPerfil.getText().toString();
                String idadeAtt = editIdadePerfil.getText().toString();
                String cidadeAtt = editCidPerfil.getText().toString();
                String endAtt = editEndPerfil.getText().toString();
                String telAtt = editTelPerfil.getText().toString();
                String nomeUsuarioAtt = editNomeUsuarioPerfil.getText().toString();
                String estadoAtt = editEstPerfil.getText().toString();
                UserFirebase.attNomeUsuario(nomeAtt);

                //salvar no banco
                usuarioLogado.setNome(nomeAtt);
                usuarioLogado.setIdade(idadeAtt);
                usuarioLogado.setCidade(cidadeAtt);
                usuarioLogado.setEndereco(endAtt);
                usuarioLogado.setTelefone(telAtt);
                usuarioLogado.setUsuario(nomeUsuarioAtt);
                usuarioLogado.setEstado(estadoAtt);
                usuarioLogado.atualizar();
                Toast.makeText(getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
        //Configuração para mudar foto;
        textAlterarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if(foto.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(foto, SELECTION_GALERY);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Bitmap imagem = null;
            try{
                //Galeria de fotos
                switch (requestCode){
                    case SELECTION_GALERY:
                        Uri localImagemSelect = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelect);
                        break;
                }

                //Carregar imagem
                if(imagem != null){
                    imageEditPerfil.setImageBitmap(imagem);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                    byte[] dadosImagem = baos.toByteArray();

                    //Salvar imagem no storage
                    StorageReference imgRef = storageRef.child("imagens")
                            .child("perfil")
                            .child(idUsuario + ".jpeg");
                    UploadTask uploadTask = imgRef.putBytes(dadosImagem);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditPerfilActivity.this,
                                    "Erro ao carregar a imagem", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {



                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //Recuperar local da foto
                            Task<Uri> url = taskSnapshot.getStorage().getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri url) {
                                    attPicUser(url);
                                }
                            });

                            Toast.makeText(EditPerfilActivity.this,
                                    "Sucesso ao carregar a imagem", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    private void attPicUser(Uri url){
        //atualizar foto
        UserFirebase.attPicUser(url);
        //atualizar no firebase
        usuarioLogado.setPicPath(url.toString());
        usuarioLogado.atualizar();
        Toast.makeText(EditPerfilActivity.this,
                "Foto atualizada", Toast.LENGTH_SHORT).show();
    }
    //Metodo para iniciar os componentes
    public void initCampos() {
        imageEditPerfil = findViewById(R.id.imageEditPerfil);
        editNomeCompletoPerfil = findViewById(R.id.editTextNome);
        editIdadePerfil = findViewById(R.id.editTextIdade);
        editEmailPerfil = findViewById(R.id.editTextEmail);
        editEstPerfil = findViewById(R.id.editTextEstado);
        editCidPerfil = findViewById(R.id.editTextCidade);
        editEndPerfil = findViewById(R.id.editTextEndereco);
        editTelPerfil = findViewById(R.id.editTextTelefone);
        editNomeUsuarioPerfil = findViewById(R.id.editTextNomeUsuario);
        btnSalvarEdit = findViewById(R.id.buttonSalvarEdit);
        editEmailPerfil.setFocusable(false);
        textAlterarFoto = findViewById(R.id.textAlterarFoto);
    }
}
