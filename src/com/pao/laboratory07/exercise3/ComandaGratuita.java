package com.pao.laboratory07.exercise3;

import java.util.Locale;

public final class ComandaGratuita extends Comanda {
    public ComandaGratuita(String nume, String client) {
        super(nume, client);
    }

    @Override
    public double calculPretFinal() {
        return 0.0;
    }

    @Override
    public String format(boolean includeStatus) {
        String status = includeStatus ? " [PLACED]" : "";
        return String.format(Locale.US, "GIFT: %s, gratuit%s - client: %s",
                nume, status, client);
    }
}