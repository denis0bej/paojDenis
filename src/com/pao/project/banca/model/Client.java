package com.pao.project.banca.model;

import java.util.Objects;

public class Client extends Utilizator implements Comparable<Client> {
    private String adresa;
    private String numarTelefon;

    public Client(String cnp, String numeComplet, String adresa, String numarTelefon) {
        super(cnp, numeComplet);
        this.adresa = adresa;
        this.numarTelefon = numarTelefon;
    }

    @Override
    public String getTipUtilizator() {
        return "CLIENT_BANCAR";
    }

    // Getteri si Setteri
    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) { this.adresa = adresa; }

    public String getNumarTelefon() { return numarTelefon; }
    public void setNumarTelefon(String numarTelefon) { this.numarTelefon = numarTelefon; }

    // Suprascriere necesara pentru colectii de tip Set/Map
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(cnp, client.cnp); // CNP-ul este unic
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnp);
    }

    @Override
    public String toString() {
        return "Client{" + "CNP='" + cnp + '\'' + ", Nume='" + numeComplet + '\'' + '}';
    }

    // Necesara pentru a sorta clientii alfabetic (Cerinta din Etapa 1)
    @Override
    public int compareTo(Client altClient) {
        return this.numeComplet.compareToIgnoreCase(altClient.numeComplet);
    }
}