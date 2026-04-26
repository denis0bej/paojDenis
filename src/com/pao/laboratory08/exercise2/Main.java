package com.pao.laboratory08.exercise2;

import java.io.*;
import java.util.*;

// Importăm clasele din exercițiul 1
import com.pao.laboratory08.exercise1.Student;
import com.pao.laboratory08.exercise1.Adresa;

public class Main {
    // Folosim exact calea absolută de pe PC-ul tău (cu backslash-uri dublate)
    private static final String FILE_PATH = "C:\\Users\\howdo\\Desktop\\tests\\paoj-2026\\src\\com\\pao\\laboratory08\\tests\\studenti.txt";

    public static void main(String[] args) throws Exception {
        List<Student> studenti = new ArrayList<>();

        // 1. Citește studenții direct din calea absolută
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] parts = linie.split(",");
                if (parts.length == 4) {
                    String nume = parts[0].trim();
                    int varsta = Integer.parseInt(parts[1].trim());
                    String oras = parts[2].trim();
                    String strada = parts[3].trim();

                    studenti.add(new Student(nume, varsta, new Adresa(oras, strada)));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Nu am gasit fisierul la calea: " + FILE_PATH);
            return;
        }

        // 2. Citește pragul de vârstă din stdin cu Scanner
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int prag = scanner.nextInt();

        // 3. Filtrează studenții cu varsta >= prag
        List<Student> studentiFiltrati = new ArrayList<>();
        for (Student s : studenti) {
            if (s.getVarsta() >= prag) {
                studentiFiltrati.add(s);
            }
        }

        // 4. Scrie filtrații în "rezultate.txt" cu BufferedWriter
        try (BufferedWriter fout = new BufferedWriter(new FileWriter("rezultate.txt"))) {
            for (Student s : studentiFiltrati) {
                fout.write(s.toString());
                fout.newLine();
            }
        }

        // 5. Afișează sumarul la consolă fix în formatul cerut
        System.out.println("Filtru: varsta >= " + prag);
        System.out.println("Rezultate: " + studentiFiltrati.size() + " studenti\n");

        for (Student s : studentiFiltrati) {
            System.out.println(s);
        }

        System.out.println("\nScris in: rezultate.txt");
    }
}