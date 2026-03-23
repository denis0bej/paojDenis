package com.pao.laboratory05.angajati;

import com.pao.laboratory05.biblioteca.Carte;

import java.util.Arrays;

public class AngajatService {

    void findByDepartament(String numeDept){
        boolean found = false;
        for(Angajat a : angajati){
            if(a.getDepartament().nume().equalsIgnoreCase(numeDept)) {
                System.out.println(a);
                found = true;
            }
        }
        if(!found)
            System.out.println("Niciun angajat în departamentul: " + numeDept);
    }

    void listBySalary(){
        Angajat[] clone = new Angajat[angajati.length];
        System.arraycopy(angajati, 0, clone, 0, angajati.length);
        Arrays.sort(clone);
        for(Angajat a : clone){
            System.out.println(a);
        }
    }

    void printAll(){
        for(Angajat a : angajati){
            System.out.println(a.toString());
        }
    }

    void addAngajat(Angajat angajat){
        Angajat[] new_angajati = new Angajat[angajati.length + 1];
        System.arraycopy(angajati, 0, new_angajati, 0, angajati.length);
        new_angajati[angajati.length] = angajat;
        angajati = new_angajati;
        System.out.println("Angajatul '" + angajat.getNume() + "' a fost adaugat cu succes!");
    }
    
    private AngajatService(){
        angajati = new Angajat[0];
    }
    
    public static AngajatService getInstance(){
        if(instance == null){
            instance = new AngajatService();
        }
        return instance;
    }
    static private AngajatService instance = null;
    private Angajat[] angajati;
}
