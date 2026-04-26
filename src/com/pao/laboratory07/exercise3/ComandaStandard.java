package com.pao.laboratory07.exercise3;

import java.util.Locale;

public final class ComandaStandard extends Comanda {
    private double pret;

    public ComandaStandard(String nume, double pret, String client) {
        super(nume, client);
        this.pret = pret;
    }

    @Override
    public double calculPretFinal() {
        return pret;
    }

    @Override
    public String format(boolean includeStatus) {
        String status = includeStatus ? " [PLACED]" : "";
        return String.format(Locale.US, "STANDARD: %s, pret: %.2f lei%s - client: %s",
                nume, pret, status, client);
    }
}