package com.pao.project.banca.model;

public class ContEconomii extends ContBancar {
    private double rataDobanda; // De exemplu, 0.05 pentru o dobândă de 5%

    public ContEconomii(String iban, Client titular, double rataDobanda) {
        super(iban, titular);
        this.rataDobanda = rataDobanda;
    }

    @Override
    public String getTipCont() {
        return "CONT_ECONOMII";
    }

    // Metodă specifică doar contului de economii
    public void adaugaDobanda() {
        if (sold > 0) {
            double valoareDobanda = this.sold * rataDobanda;
            // Ne folosim de metoda deja implementată în clasa de bază ContBancar
            this.depunere(valoareDobanda);
            System.out.println("Dobândă adăugată: " + valoareDobanda + " pentru contul " + iban);
        }
    }

    // Getteri și setteri specifici
    public double getRataDobanda() {
        return rataDobanda;
    }

    public void setRataDobanda(double rataDobanda) {
        this.rataDobanda = rataDobanda;
    }
}