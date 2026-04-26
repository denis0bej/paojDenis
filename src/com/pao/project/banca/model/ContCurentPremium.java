package com.pao.project.banca.model;

public class ContCurentPremium extends ContCurent {
    private double puncteLoialitate;

    public ContCurentPremium(String iban, Client titular) {
        super(iban, titular, 5000.0); // Overdraft mare implicit
        this.puncteLoialitate = 0;
    }

    @Override
    public String getTipCont() { return "CONT_CURENT_PREMIUM"; }
}