package com.pao.laboratory07.exercise2;

public final class ComandaRedusa extends Comanda{
    private double pret;
    private double discount;
    public ComandaRedusa(String nume, double pret, double discount) {
        super(nume);
        this.pret = pret;
        this.discount = discount;
    }
    @Override
    public double pretFinal() {
        return this.pret * (1 - this.discount/100);
    }

    @Override
    public String descriere() {
        return String.format("DISCOUNTED: %s, pret: %.2f lei (%.0f%%) [PLACED]", this.nume, pretFinal(), -this.discount);
    }
}
