package br.unb.meau.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.firestore.FirebaseFirestore;

public class Animal implements Serializable {

    private String dono;
    private String nome;
    private String idade;
    private String especie;
    private String localizacao;
    private String porte;
    private String sexo;
    private HashMap<String, Boolean> temperamento;
    private HashMap<String, Boolean> saude;
    private String doencas;
    private String sobre;
    private int imagem;
    private Adocao adoptData;
    private Ajuda helpData;
    private Apadrinhamento provideData;

    public Adocao getAdoptData() {
        return adoptData;
    }

    public void setAdoptData(Adocao adoptData) {
        this.adoptData = adoptData;
    }

    public Ajuda getHelpData() {
        return helpData;
    }

    public void setHelpData(Ajuda helpData) {
        this.helpData = helpData;
    }

    public Apadrinhamento getProvideData() {
        return provideData;
    }

    public void setProvideData(Apadrinhamento provideData) {
        this.provideData = provideData;
    }

    public Animal() {
        this.nome = "";
        this.dono = "";
        this.idade = "";
        this.especie = "";
        this.localizacao = "";
        this.porte = "";
        this.sexo = "";
        this.imagem = 0;
        this.temperamento = new HashMap<String, Boolean>();
        this.saude = new HashMap<String, Boolean>();
        this.doencas = "";
        this.sobre = "";
        this.adoptData = new Adocao();
        this.helpData = new Ajuda();
        this.provideData = new Apadrinhamento();
    }

    public Animal(String nome, String idade, String especie, String localizacao, String porte, String sexo, int imagem) {
        this.dono = "";
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.localizacao = localizacao;
        this.porte = porte;
        this.sexo = sexo;
        this.imagem = imagem;
        this.temperamento = new HashMap<String, Boolean>();
        this.saude = new HashMap<String, Boolean>();
        this.doencas = "";
        this.sobre = "";
    }

    public Animal(String dono, String nome, String idade, String especie, String localizacao, String porte, String sexo, HashMap<String, Boolean> temperamento, HashMap<String, Boolean> saude, String doencas, String sobre, int imagem) {
        this.dono = dono;
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.localizacao = localizacao;
        this.porte = porte;
        this.sexo = sexo;
        this.temperamento = temperamento;
        this.saude = saude;
        this.doencas = doencas;
        this.sobre = sobre;
        this.imagem = imagem;
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public HashMap<String, Boolean> getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(HashMap<String, Boolean> temperamento) {
        this.temperamento = temperamento;
    }

    public HashMap<String, Boolean> getSaude() {
        return saude;
    }

    public void setSaude(HashMap<String, Boolean> saude) {
        this.saude = saude;
    }

    public String getDoencas() {
        return doencas;
    }

    public void setDoencas(String doencas) {
        this.doencas = doencas;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public Map<String, Object> convertMap() {

        HashMap<String, Object> animalMap = new HashMap<>();
        animalMap.put("name", getNome());
        animalMap.put("gender", getSexo());
        animalMap.put("age", getIdade());
        animalMap.put("size", getPorte());
        animalMap.put("temper", getTemperamento());
        animalMap.put("health", getSaude());
        animalMap.put("about", getSobre());
        if (getAdoptData() != null) {
            animalMap.put("adopting", getAdoptData().convertMap());
        } else if (getProvideData() != null) {
            animalMap.put("providing", getProvideData().convertMap());
        }
        if (getHelpData() != null) {
            animalMap.put("helping", getHelpData().convertMap());
        }

        return animalMap;
    }

    public void salvar() {
        Map<String, Object> animal = convertMap();
        FirebaseFirestore dbRef = FirebaseFirestore.getInstance();
        animal.put("owner", dbRef.document(getDono()));
        dbRef.collection("animals").add(animal);
    }
}
