package com.pao.laboratory07.exercise3;

public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita {
    protected String nume;
    protected String client;

    public Comanda(String nume, String client) {
        this.nume = nume;
        this.client = client;
    }

    public String getNume() { return nume; }
    public String getClient() { return client; }

    public abstract double calculPretFinal();

    public abstract String format(boolean includeStatus);

    @Override
    public String toString() {
        return format(true);
    }
}