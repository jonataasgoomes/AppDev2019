package br.unb.meau.model;

import android.media.Image;

import java.util.ArrayList;

public class Animal {

    private String nome;
    private String idade;
    private String especie;
    private String localizacao;
    private String porte;
    private ArrayList<String> temperamento;
    private ArrayList<String> saude;
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
        this.idade = "";
        this.especie = "";
        this.localizacao = "";
        this.porte = "";
        this.imagem = 0;
        this.temperamento = new ArrayList<String>();
        this.saude = new ArrayList<String>();
        this.doencas = "";
        this.sobre = "";
        this.adoptData = new Adocao();
        this.helpData = new Ajuda();
        this.provideData = new Apadrinhamento();
    }

    public Animal(String nome, String idade, String especie, String localizacao, String porte, int imagem) {
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.localizacao = localizacao;
        this.porte = porte;
        this.imagem = imagem;
        this.temperamento = new ArrayList<String>();
        this.saude = new ArrayList<String>();
        this.doencas = "";
        this.sobre = "";
    }

    public Animal(String nome, String idade, String especie, String localizacao, String porte, ArrayList<String> temperamento, ArrayList<String> saude, String doencas, String sobre, int imagem) {
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.localizacao = localizacao;
        this.porte = porte;
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

    public ArrayList<String> getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(ArrayList<String> temperamento) {
        this.temperamento = temperamento;
    }

    public ArrayList<String> getSaude() {
        return saude;
    }

    public void setSaude(ArrayList<String> saude) {
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
}
