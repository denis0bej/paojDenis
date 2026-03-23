package com.pao.laboratory05.audit;

public class Angajat implements Comparable<Angajat>{

    @Override
    public String toString() {
        return "Angajat{nume='" + nume + "', departament='" + departament.toString() + "', salariu=" + salariu + "}";
    }
    public String getNume(){
        return nume;
    }
    public Departament getDepartament(){
        return departament;
    }
    public double getSalariu(){
        return salariu;
    }
    @Override
    public int compareTo(Angajat o) {
        return Double.compare(o.getSalariu(), salariu);
    }
    public Angajat(String nume, Departament departament, double salariu){
        this.nume = nume;
        this.departament = departament;
        this.salariu = salariu;
    }
    private String nume;
    private Departament departament;
    private double salariu;
}
