package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer> {

    private double sold;
    private boolean autentificat;

    public Inginer(String nume, String prenume, String telefon, double salariu, double sold) {
        super(nume, prenume, telefon, salariu);
        this.sold = sold;
        this.autentificat = false;
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("Userul nu poate fi null sau gol!");
        }
        if (parola == null || parola.isEmpty()) {
            throw new IllegalArgumentException("Parola nu poate fi null sau goala!");
        }
        this.autentificat = true;
        System.out.println("[Inginer] " + nume + " " + prenume + " s-a autentificat cu succes.");
    }

    @Override
    public double consultareSold() {
        System.out.println("[Inginer] Sold curent pentru " + nume + ": " + sold + " RON");
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0) {
            System.out.println("[Inginer] Suma invalida: " + suma);
            return false;
        }
        if (suma > sold) {
            System.out.println("[Inginer] Sold insuficient pentru plata de " + suma + " RON.");
            return false;
        }
        sold -= suma;
        System.out.println("[Inginer] Plata de " + suma + " RON efectuata. Sold ramas: " + sold + " RON.");
        return true;
    }

    @Override
    public int compareTo(Inginer alt) {
        return this.nume.compareTo(alt.nume);
    }

    @Override
    public String toString() {
        return "Inginer{" + super.toString() + ", sold: " + sold + " RON}";
    }
}
