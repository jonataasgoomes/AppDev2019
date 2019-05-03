package br.unb.meau.model;


import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Animal implements Serializable {

    private String id;
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
    private String imagem;
    private Adocao adoptData;
    private Ajuda helpData;
    private Apadrinhamento provideData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    }

    public Animal(String nome, String idade, String especie, String localizacao, String porte, String sexo, String imagem) {
        this.dono = "";
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.localizacao = localizacao;
        this.porte = porte;
        this.sexo = sexo;
        this.imagem = "";
        this.temperamento = new HashMap<String, Boolean>();
        this.saude = new HashMap<String, Boolean>();
        this.doencas = "";
        this.sobre = "";
    }

    public Animal(String dono, String nome, String idade, String especie, String localizacao, String porte, String sexo, HashMap<String, Boolean> temperamento, HashMap<String, Boolean> saude, String doencas, String sobre, String imagem) {
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
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
        animalMap.put("nome", getNome());
        animalMap.put("localizacao", getLocalizacao());
        animalMap.put("sexo", getSexo());
        animalMap.put("idade", getIdade());
        animalMap.put("porte", getPorte());
        animalMap.put("temperamento", getTemperamento());
        animalMap.put("saude", getSaude());
        animalMap.put("sobre", getSobre());
        animalMap.put("doencas", getDoencas());
        animalMap.put("imagem", getImagem());
        if (getAdoptData() != null) {
            animalMap.put("AdoptData", getAdoptData().convertMap());
        } else if (getProvideData() != null) {
            animalMap.put("ProvideData", getProvideData().convertMap());
        }
        if (getHelpData() != null) {
            animalMap.put("HelpData", getHelpData().convertMap());
        }

        return animalMap;
    }

    public void salvar() {
        Map<String, Object> animal = convertMap();
        FirebaseFirestore dbRef = FirebaseFirestore.getInstance();
        animal.put("dono", getDono());
        dbRef.collection("animals").add(animal);
    }
}
