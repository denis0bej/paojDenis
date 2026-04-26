package com.pao.laboratory07.exercise3;

import java.util.Locale;

public final class ComandaRedusa extends Comanda {
    private double pret;
    private double discountProcent;

    public ComandaRedusa(String nume, double pret, double discountProcent, String client) {
        super(nume, client);
        this.pret = pret;
        this.discountProcent = discountProcent;
    }

    public double getDiscountProcent() {
        return discountProcent;
    }

    @Override
    public double calculPretFinal() {
        return pret - (pret * discountProcent / 100.0);
    }

    @Override
    public String format(boolean includeStatus) {
        String status = includeStatus ? " [PLACED]" : "";
        return String.format(Locale.US, "DISCOUNTED: %s, pret: %.2f lei (-%.0f%%)%s - client: %s",
                nume, calculPretFinal(), discountProcent, status, client);
    }
}