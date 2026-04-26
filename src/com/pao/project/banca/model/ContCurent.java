package com.pao.project.banca.model;

public class ContCurent extends ContBancar {
    private double limitaOverdraft;

    public ContCurent(String iban, Client titular, double limitaOverdraft) {
        super(iban, titular);
        this.limitaOverdraft = limitaOverdraft;
    }

    @Override
    public String getTipCont() { return "CONT_CURENT"; }
}