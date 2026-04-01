package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS {

    private double sold;
    private boolean autentificat;

    private List<String> smsTrimise;

    public PersoanaJuridica(String nume, String prenume, String telefon, double sold) {
        super(nume, prenume, telefon);
        this.sold = sold;
        this.autentificat = false;
        this.smsTrimise = new ArrayList<>();
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
        System.out.println("[PersoanaJuridica] " + nume + " autentificat cu succes.");
    }

    @Override
    public double consultareSold() {
        System.out.println("[PersoanaJuridica] Sold curent pentru " + nume + ": " + sold + " RON");
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0) {
            System.out.println("[PersoanaJuridica] Suma invalida: " + suma);
            return false;
        }
        if (suma > sold) {
            System.out.println("[PersoanaJuridica] Sold insuficient pentru plata de " + suma + " RON.");
            return false;
        }
        sold -= suma;
        System.out.println("[PersoanaJuridica] Plata de " + suma + " RON efectuata. Sold ramas: " + sold + " RON.");
        return true;
    }

    @Override
    public boolean trimiteSMS(String mesaj) {
        if (mesaj == null || mesaj.isEmpty()) {
            System.out.println("[SMS] Mesaj invalid (null sau gol). SMS nu a fost trimis.");
            return false;
        }
        if (telefon == null || telefon.isEmpty()) {
            System.out.println("[SMS] " + nume + " nu are numar de telefon. SMS nu a fost trimis.");
            return false;
        }
        smsTrimise.add(mesaj);
        System.out.println("[SMS] SMS trimis catre " + telefon + ": \"" + mesaj + "\"");
        return true;
    }

    public List<String> getSmsTrimise() {
        return List.copyOf(smsTrimise);
    }

    @Override
    public String toString() {
        return "PersoanaJuridica{" + super.toString() + ", sold: " + sold + " RON, SMS trimise: " + smsTrimise.size() + "}";
    }
}
