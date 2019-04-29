package br.unb.meau.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Apadrinhamento {
    private boolean terms;
    private boolean visit;
    private boolean financialSupport;
    private boolean[] financialNeeds = new boolean[3];

    public Apadrinhamento() {
    }

    public Apadrinhamento(boolean terms, boolean visit, boolean financialSupport, boolean[] financialNeeds) {
        this.terms = terms;
        this.visit = visit;
        this.financialSupport = financialSupport;
        this.financialNeeds = financialNeeds;
    }

    public boolean isTerms() {
        return terms;
    }

    public void setTerms(boolean terms) {
        this.terms = terms;
    }

    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public boolean isFinancialSupport() {
        return financialSupport;
    }

    public void setFinancialSupport(boolean financialSupport) {
        this.financialSupport = financialSupport;
    }

    public boolean[] getFinancialNeeds() {
        return financialNeeds;
    }

    public void setFinancialNeeds(boolean[] financialNeeds) {
        this.financialNeeds = financialNeeds;
    }

    public Map<String, Object> convertMap() {
        HashMap<String, Object> myMap = new HashMap<>();
        myMap.put("ajudaFinanceira", isFinancialSupport());
        myMap.put("termos", isTerms());
        myMap.put("visitas", isVisit());
        myMap.put("necessidadesFinanceiras", getFinancialNeeds());
        return myMap;
    }
}
