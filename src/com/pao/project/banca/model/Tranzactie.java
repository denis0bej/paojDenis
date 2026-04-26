package com.pao.project.banca.model;

import java.time.LocalDateTime;

public final class Tranzactie {
    private final String idTranzactie;
    private final String ibanSursa;
    private final String ibanDestinatie;
    private final double suma;
    private final LocalDateTime dataTimp;

    public Tranzactie(String idTranzactie, String ibanSursa, String ibanDestinatie, double suma) {
        this.idTranzactie = idTranzactie;
        this.ibanSursa = ibanSursa;
        this.ibanDestinatie = ibanDestinatie;
        this.suma = suma;
        this.dataTimp = LocalDateTime.now();
    }

    public String getIdTranzactie() { return idTranzactie; }
    public String getIbanSursa() { return ibanSursa; }
    public String getIbanDestinatie() { return ibanDestinatie; }
    public double getSuma() { return suma; }
    public LocalDateTime getDataTimp() { return dataTimp; }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "id='" + idTranzactie + '\'' +
                ", de la='" + ibanSursa + '\'' +
                ", catre='" + ibanDestinatie + '\'' +
                ", suma=" + suma +
                ", data=" + dataTimp +
                '}';
    }
}