package br.unb.meau.model;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String id;
    private String nome;
    private String idade;
    private String email;
    private String estado;
    private String cidade;
    private String endereco;
    private String telefone;
    private String usuario;
    private String senha;
    private String picPath;


    public Usuario() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public void salvar() {
        Map<String,Object> usuario = convertMap();
        FirebaseFirestore dbRef = FirebaseFirestore.getInstance();
        dbRef.collection("user").document(getId()).set(usuario);
    }
    public void atualizar() {
        Map<String,Object> usuario = convertMap();
        FirebaseFirestore dbRef = FirebaseFirestore.getInstance();
        DocumentReference userRef = dbRef.collection("user").document(getId());
        userRef.update(usuario);
    }

    public Map<String, Object> convertMap(){

        HashMap<String,Object> userMap = new HashMap<>();
        userMap.put("id",getId());
        userMap.put("nome",getNome());
        userMap.put("email", getEmail());
        userMap.put("idade",getIdade());
        userMap.put("estado",getEstado());
        userMap.put("cidade",getCidade());
        userMap.put("endereco",getEndereco());
        userMap.put("telefone",getTelefone());
        userMap.put("usuario",getUsuario());
        userMap.put("picPath",getPicPath());

        return userMap;
    }
}
