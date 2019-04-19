package br.unb.meau.helper;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import br.unb.meau.model.Usuario;

public class UserFirebase {

    public static FirebaseUser getUsuarioAtual() {

        FirebaseAuth usuario = ConfigFirebase.getFirebaseAuth();
        return usuario.getCurrentUser();

    }
    public static String getIdUser(){
        return getUsuarioAtual().getUid();
    }

    public static void attNomeUsuario(String nome) {
        try {
            //Usuário logado
            FirebaseUser usuarioLogado = getUsuarioAtual();
            //Configurar objeto para alteraçõ do perfil
            UserProfileChangeRequest profile = new UserProfileChangeRequest
                    .Builder().setDisplayName(nome).build();
            usuarioLogado.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Log.d("Perfil", "Erro ao atualizar nome de perfil");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void attPicUser(Uri url) {
        try {
            //Usuário logado
            FirebaseUser usuarioLogado = getUsuarioAtual();
            //Configurar objeto para alteração do perfil
            UserProfileChangeRequest profile = new UserProfileChangeRequest
                    .Builder().setPhotoUri(url)
                    .build();
            usuarioLogado.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Log.d("Perfil", "Erro ao atualizar foto de perfil");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Usuario getDadosUsuarioLogado() {

        FirebaseUser firebaseUser = getUsuarioAtual();

        Usuario usuario = new Usuario();
        usuario.setNome(firebaseUser.getDisplayName());
        usuario.setEmail(firebaseUser.getEmail());
        usuario.setId(firebaseUser.getUid());

        if (firebaseUser.getPhotoUrl() == null) {
            usuario.setPicPath("");
        } else{
            usuario.setPicPath(firebaseUser.getPhotoUrl().toString());
        }
        return usuario;
    }
}