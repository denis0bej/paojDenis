package com.pao.laboratory06.exercise2;

public abstract class Colaborator {

    public Colaborator() {
    }

    public abstract double calculeazaVenitNetAnual();

    public String getNume() {return nume;}
    public String getPrenume() {return prenume;}
    public int getVenit_brut_lunar() {return venit_brut_lunar;}
    public void setNume(String nume) {this.nume = nume;}
    public void setPrenume(String prenume) {this.prenume = prenume;}
    public void setVenit_brut_lunar(int venit_brut_lunar) {this.venit_brut_lunar = venit_brut_lunar;}

    private String nume;
    private String prenume;
    private int venit_brut_lunar;

    public abstract void afiseaza();
    public abstract TipColaborator getTip();
}
