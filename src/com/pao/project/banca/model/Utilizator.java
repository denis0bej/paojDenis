package com.pao.project.banca.model;

public abstract class Utilizator {
    protected String cnp;
    protected String numeComplet;

    public Utilizator(String cnp, String numeComplet) {
        this.cnp = cnp;
        this.numeComplet = numeComplet;
    }

    public abstract String getTipUtilizator();

    public String getCnp() { return cnp; }
    public void setCnp(String cnp) { this.cnp = cnp; }

    public String getNumeComplet() { return numeComplet; }
    public void setNumeComplet(String numeComplet) { this.numeComplet = numeComplet; }
}