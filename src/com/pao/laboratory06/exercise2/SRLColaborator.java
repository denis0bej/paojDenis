package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class SRLColaborator extends Colaborator implements IOperatiiCitireScriere, PersoanaJuridica{
    public SRLColaborator() {
        super();
    }
    @Override
    public void citeste(Scanner in) {
        setNume(in.next());
        setPrenume(in.next());
        setVenit_brut_lunar(in.nextInt());
        setCheltuileiLunare(in.nextInt());
    }

    @Override
    public void afiseaza() {
        System.out.println(tipContract() + ": " + getNume() + " " + getPrenume() + ", venit net anual: " + String.format("%.2f", calculeazaVenitNetAnual()) + " lei");
    }

    @Override
    public TipColaborator getTip() {
        return TipColaborator.SRL;
    }

    @Override
    public String tipContract() {
        return "SRL";
    }

    @Override
    public boolean areBonus(){
        return bonus.equalsIgnoreCase("da");
    }

    public void setCheltuileiLunare(int cheltuilei_lunare) {this.cheltuilei_lunare = cheltuilei_lunare;}
    public int getCheltuileiLunare() {return cheltuilei_lunare;}

    @Override
    public double calculeazaVenitNetAnual() {
        return 0.84 * (getVenit_brut_lunar() - cheltuilei_lunare) * 12;
    }

    private int cheltuilei_lunare;
    private String bonus;
}
