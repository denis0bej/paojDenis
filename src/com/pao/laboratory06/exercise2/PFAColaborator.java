package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class PFAColaborator extends Colaborator implements IOperatiiCitireScriere, PersoanaFizica {
    public PFAColaborator() {
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
        return TipColaborator.PFA;
    }

    @Override
    public String tipContract() {
        return "PFA";
    }

    @Override
    public boolean areBonus(){
        return bonus.equalsIgnoreCase("da");
    }

    public void setCheltuileiLunare(int cheltuilei_lunare) {this.cheltuilei_lunare = cheltuilei_lunare;}

    public int getCheltuileiLunare() {return cheltuilei_lunare;}

    @Override
    public double calculeazaVenitNetAnual() {
        double venit_net = 1.0 * (getVenit_brut_lunar() - cheltuilei_lunare) * 12;
        double impozit_pe_venit = 0.1 * venit_net;
        double cass;
        if(venit_net < 6 * 48600)
            cass = 0.1 * 6 * 48600;
        else if(venit_net <= 72 * 48600)
            cass = 0.1 * venit_net;
        else
            cass = 0.1 * 72 * 48600;

        double cas;
        if(venit_net < 12 * 48600)
            cas = 0;
        else if(venit_net <= 24 * 48600)
            cas = 0.25 * 12 * 48600;
        else
            cas = 0.25 * 24 * 48600;

        return venit_net - impozit_pe_venit - cass - cas;

    }

    private int cheltuilei_lunare;
    private String bonus;
}
