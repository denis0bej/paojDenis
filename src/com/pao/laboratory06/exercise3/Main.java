package com.pao.laboratory06.exercise3;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // 1. ENUM ConstanteFinanciare
        System.out.println("1. Constante Financiare (enum)");
        for (ConstanteFinanciare c : ConstanteFinanciare.values()) {
            System.out.printf(" %-15s = %.2f%n", c.name(), c.getValoare());
        }
        double tva = ConstanteFinanciare.TVA.getValoare();
        System.out.println(" TVA aplicat la 1000 RON: " + (1000 * tva) + " RON");
        System.out.println();

        // 2. SORTARE ARRAY DE INGINERI
        System.out.println("2. Array de Ingineri — sortare dupa NUME (Comparable)");
        Inginer[] ingineri = {
            new Inginer("Popescu", "Ion", "0722111111", 6500.0, 12000.0),
            new Inginer("Andrei", "Maria", "0733222222", 8200.0, 25000.0),
            new Inginer("Mihai", "Radu", null, 5000.0,  8000.0), // fara telefon
            new Inginer("Barbu", "Elena", "0744333333", 9100.0, 30000.0),
            new Inginer("Zaharia", "Liviu", "0755444444", 4800.0, 5000.0),
        };

        Arrays.sort(ingineri); // ordine naturala, dupa nume
        System.out.println(" Dupa sortare alfabetica dupa nume:");
        for (Inginer i : ingineri) {
            System.out.println("  " + i);
        }
        System.out.println();
        // 3. SORTARE INGINERI — dupa salariu
        System.out.println("3. Array de Ingineri — sortare dupa SALARIU descrescator (Comparator)");
        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        System.out.println(" Dupa sortare descrescatoare dupa salariu:");
        for (Inginer i : ingineri) {
            System.out.println("    " + i);
        }
        System.out.println();

        // 4. REFERINTA DE TIP PlataOnline — acces limitat la interfata

        System.out.println("4. Acces Inginer prin referinta de tip PlataOnline");
        // Prin referinta PlataOnline nu avem acces la getSalariu(), compareTo() etc.
        PlataOnline clientBancar = new Inginer("Georgescu", "Dan", "0766555555", 7000.0, 15000.0);
        clientBancar.autentificare("dan.georgescu", "parola123");
        clientBancar.consultareSold();
        boolean plataOk = clientBancar.efectuarePlata(3000.0);
        System.out.println(" Plata reusita: " + plataOk);
        // clientBancar.getSalariu()  //metoda nu exista pe PlataOnline
        System.out.println(" (metodele specifice Inginer nu sunt accesibile prin referinta PlataOnline)\n");

        // 5. PERSOANA JURIDICA prin referinta PlataOnlineSMS + stocare SMS
        System.out.println("5. PersoanaJuridica prin referinta PlataOnlineSMS");
        PersoanaJuridica firma = new PersoanaJuridica("TechSoft SRL", "—", "0799000111", 50000.0);
        PlataOnlineSMS clientSMS = firma; // referinta de tip PlataOnlineSMS

        clientSMS.autentificare("techsoft", "secretParola!");
        clientSMS.consultareSold();
        clientSMS.efectuarePlata(12000.0);

        // Trimitere SMS valida
        clientSMS.trimiteSMS("Plata de 12000 RON confirmata.");
        clientSMS.trimiteSMS("Soldul dumneavoastra este actualizat.");

        // Acces la lista SMS (prin referinta concreta firma, nu prin interfata)
        System.out.println(" SMS-uri trimise de " + firma.getNume() + ": " + firma.getSmsTrimise());
        System.out.println();

        // 6. EDGE CASE — PersoanaJuridica FARA telefon
        System.out.println("6. Edge case: PersoanaJuridica fara telefon");
        PersoanaJuridica firmaFaraTel = new PersoanaJuridica("NoPhone SRL", "—", null, 20000.0);
        firmaFaraTel.autentificare("nophone", "pass");
        boolean rezultat = firmaFaraTel.trimiteSMS("Confirmare tranzactie");
        System.out.println(" SMS trimis: " + rezultat + " (asteptat: false)");
        System.out.println();

        // 7. EDGE CASE — mesaj SMS null sau gol
        System.out.println("7. Edge case: mesaj SMS null sau gol");
        boolean r1 = firma.trimiteSMS(null);
        boolean r2 = firma.trimiteSMS("   ".trim()); // dupa trim devine gol
        System.out.println(" SMS cu mesaj null: " + r1 + " (asteptat: false)");
        System.out.println(" SMS cu mesaj gol:  " + r2 + " (asteptat: false)");
        System.out.println();

        // 8. EDGE CASE — autentificare cu user null (IllegalArgumentException)
        System.out.println("8. Edge case: autentificare cu user null");
        try {
            clientBancar.autentificare(null, "parola");
        } catch (IllegalArgumentException e) {
            System.out.println(" Exceptie prinsa corect: " + e.getMessage());
        }

        System.out.println();

        // 9. EDGE CASE — trimiteSMS apelat pe entitate fara capabilitate SMS
        // (Inginer nu implementeaza PlataOnlineSMS)
        System.out.println("9. Edge case: trimiteSMS pe Inginer (fara capabilitate SMS)");
        Inginer inginerOarecare = ingineri[0];
        try {
            // Inginerii NU implementeaza PlataOnlineSMS — cast ilegal, prindem ClassCastException
            // Aceasta este comportamentul corect: la runtime, cast-ul esueaza.
            PlataOnlineSMS incercare = (PlataOnlineSMS) inginerOarecare;
            incercare.trimiteSMS("test");
        } catch (ClassCastException e) {
            // Design decision: Inginer nu implementeaza PlataOnlineSMS,
            // deci cast-ul arunca ClassCastException la runtime — comportament corect.
            System.out.println(" ClassCastException prinsa: Inginer nu suporta PlataOnlineSMS.");
            System.out.println(" Mesaj: " + e.getMessage());
        }
        System.out.println();

        // 10. PLATA cu sold insuficient
        System.out.println("10. Edge case: plata cu sold insuficient");
        Inginer inginerSarac = new Inginer("Test", "User", "0700000000", 3000.0, 100.0);
        inginerSarac.autentificare("test.user", "pass");
        boolean plataMare = inginerSarac.efectuarePlata(5000.0);
        System.out.println(" Plata de 5000 RON cu sold 100 RON: " + plataMare + " (asteptat: false)");

        System.out.println();
        System.out.println("Demonstratie finalizata cu succes.");
    }
}
