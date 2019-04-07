package br.unb.meau.model;

import android.media.Image;

public class Animal {

    private String nome;
    private String idade;
    private String especie;
    private String localizacao;
    private String porte;
    private int imagem;

    public Animal() {
    }

    public Animal(String nome, String idade, String especie, String localizacao, String porte, int imagem) {
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.localizacao = localizacao;
        this.porte = porte;
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
}
