package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class CIMColaborator extends Colaborator implements IOperatiiCitireScriere, PersoanaFizica {
    public CIMColaborator() {
        super();
    }
    @Override
    public void citeste(Scanner in) {
        setNume(in.next());
        setPrenume(in.next());
        setVenit_brut_lunar(in.nextInt());
        bonus = in.next();
    }

    @Override
    public void afiseaza() {
        System.out.println(tipContract() + ": " + getNume() + " " + getPrenume() + ", venit net anual: " + String.format("%.2f", calculeazaVenitNetAnual()) + " lei");
    }

    @Override
    public TipColaborator getTip() {
        return TipColaborator.CIM;
    }

    @Override
    public String tipContract() {
        return "CIM";
    }

    @Override
    public boolean areBonus(){
        return bonus.equalsIgnoreCase("da");
    }

    @Override
    public double calculeazaVenitNetAnual() {
        if (bonus.equalsIgnoreCase("da")){
            return 0.55 * this.getVenit_brut_lunar() * 12 * 1.1;
        } else {
            return 0.55 * this.getVenit_brut_lunar() * 12;
        }

    }

    private String bonus;
}
