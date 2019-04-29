package br.unb.meau.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Adocao {
    private boolean terms;
    private boolean pictures;
    private boolean visit;
    private boolean postAdoptionHelp;
    private boolean[] postAdoptionPeriods = new boolean[3];

    public Adocao() {
    }

    public Adocao(boolean terms, boolean pictures, boolean visit, boolean postAdoptionHelp, boolean[] postAdoptionPeriods) {
        this.terms = terms;
        this.pictures = pictures;
        this.visit = visit;
        this.postAdoptionHelp = postAdoptionHelp;
        this.postAdoptionPeriods = postAdoptionPeriods;
    }

    public boolean isTerms() {
        return terms;
    }

    public void setTerms(boolean terms) {
        this.terms = terms;
    }

    public boolean isPictures() {
        return pictures;
    }

    public void setPictures(boolean pictures) {
        this.pictures = pictures;
    }

    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public boolean isPostAdoptionHelp() {
        return postAdoptionHelp;
    }

    public void setPostAdoptionHelp(boolean postAdoptionHelp) {
        this.postAdoptionHelp = postAdoptionHelp;
    }

    public boolean[] getPostAdoptionPeriods() {
        return postAdoptionPeriods;
    }

    public void setPostAdoptionPeriods(boolean[] postAdoptionPeriods) {
        this.postAdoptionPeriods = postAdoptionPeriods;
    }

    public Map<String, Object> convertMap() {
        HashMap<String, Object> myMap = new HashMap<>();
        myMap.put("termos", isTerms());
        myMap.put("fotos", isPictures());
        myMap.put("ajudaPosAdocao", isPostAdoptionHelp());
        myMap.put("visitas", isVisit());
        myMap.put("periodoPosAdocao", getPostAdoptionPeriods());
        return myMap;
    }
}
