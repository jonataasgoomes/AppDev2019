package br.unb.meau.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ajuda {
    private boolean food;
    private boolean financialSupport;
    private boolean medicine;
    private String medicineText;
    private boolean objects;
    private String objectsText;

    public Ajuda() {
    }

    public Ajuda(boolean food, boolean financialSupport, boolean medicine, String medicineText, boolean objects, String objectsText) {
        this.food = food;
        this.financialSupport = financialSupport;
        this.medicine = medicine;
        this.medicineText = medicineText;
        this.objects = objects;
        this.objectsText = objectsText;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFinancialSupport() {
        return financialSupport;
    }

    public void setFinancialSupport(boolean financialSupport) {
        this.financialSupport = financialSupport;
    }

    public boolean isMedicine() {
        return medicine;
    }

    public void setMedicine(boolean medicine) {
        this.medicine = medicine;
    }

    public String getMedicineText() {
        return medicineText;
    }

    public void setMedicineText(String medicineText) {
        this.medicineText = medicineText;
    }

    public boolean isObjects() {
        return objects;
    }

    public void setObjects(boolean objects) {
        this.objects = objects;
    }

    public String getObjectsText() {
        return objectsText;
    }

    public void setObjectsText(String objectsText) {
        this.objectsText = objectsText;
    }

    public Map<String, Object> convertMap() {
        HashMap<String, Object> myMap = new HashMap<>();
        myMap.put("comida", isFood());
        myMap.put("acompanhamentoFinanceiro", isFinancialSupport());
        myMap.put("remedios", isMedicine());
        myMap.put("remedioNome", getMedicineText());
        myMap.put("objetos", isObjects());
        myMap.put("objetoNome", getObjectsText());
        return myMap;
    }
}
