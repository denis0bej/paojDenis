package com.pao.project.banca.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class ContBancar {
    protected String iban;
    protected double sold;
    protected Client titular;
    protected List<Tranzactie> istoricTranzactii;

    public ContBancar(String iban, Client titular) {
        this.iban = iban;
        this.titular = titular;
        this.sold = 0.0;
        this.istoricTranzactii = new ArrayList<>();
    }

    public abstract String getTipCont();

    public void depunere(double suma) {
        if (suma > 0) {
            this.sold += suma;
            istoricTranzactii.add(new Tranzactie("DEP-" + System.currentTimeMillis(), "CASH", iban, suma));
        }
    }

    public void retragere(double suma) throws Exception {
        if (suma > sold) throw new Exception("Fonduri insuficiente!");
        this.sold -= suma;
        istoricTranzactii.add(new Tranzactie("WDR-" + System.currentTimeMillis(), iban, "CASH", suma));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContBancar that = (ContBancar) o;
        return Objects.equals(iban, that.iban); // Doua conturi sunt egale daca au acelasi IBAN
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }

    // Getteri
    public String getIban() { return iban; }
    public double getSold() { return sold; }
    public Client getTitular() { return titular; }
    public List<Tranzactie> getIstoricTranzactii() { return istoricTranzactii; }
}