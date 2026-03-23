package com.pao.laboratory05.audit;

import java.util.Scanner;

/**
 * Exercise 4 (Bonus) — Audit Log
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 4 (Bonus) — Audit"
 *
 * Extinde soluția de la Exercise 3 cu un sistem de audit bazat pe record.
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        AngajatService as = AngajatService.getInstance();
        while (true) {
            System.out.println("\n===== Gestionare Angajați =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("4. Afiseaza audit log");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");
            int choice = s.nextInt();
            s.nextLine();
            switch (choice){
                case 1 -> {
                    System.out.println("Introdu nume angajat: ");
                    String nume = s.nextLine();
                    System.out.println("Introdu nume departament: ");
                    String numeDepartament = s.nextLine();
                    System.out.println("Introdu locatie departament: ");
                    String locatieDepartament = s.nextLine();
                    System.out.println("Introdu salariu angajat: ");
                    double salariu = s.nextDouble();
                    s.nextLine();
                    Departament d = new Departament(numeDepartament, locatieDepartament);
                    Angajat a = new Angajat(nume, d, salariu);
                    as.addAngajat(a);
                }
                case 2 -> {
                    as.listBySalary();
                }
                case 3 -> {
                    System.out.println("Introdu nume departament: ");
                    String numeDepartament = s.nextLine();
                    as.findByDepartament(numeDepartament);
                }
                case 4 -> {
                    as.printAuditLog();
                }
                case 0 -> {
                    return;
                }
            }
        }
    }
}
