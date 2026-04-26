package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    // Calea către fișierul cu date — relativă la rădăcina proiectului
    private static final String FILE_PATH = "C:\\Users\\howdo\\Desktop\\tests\\paoj-2026\\src\\com\\pao\\laboratory08\\tests\\studenti.txt";

    public static void main(String[] args) throws Exception {
        List<Student> studenti = new ArrayList<>();

        // 1. Citește studenții din FILE_PATH cu BufferedReader
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
            System.err.println("Fișierul nu a fost găsit la calea: " + FILE_PATH);
            return;
        }

        // 2. Citește comanda din stdin
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return;
        }

        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return;
        }

        String[] cmdParts = input.split(" ", 2);
        String command = cmdParts[0].toUpperCase();

        // 3. Execută comanda
        switch (command) {
            case "PRINT":
                for (Student s : studenti) {
                    System.out.println(s);
                }
                break;

            case "SHALLOW":
            case "DEEP":
                if (cmdParts.length < 2) {
                    System.out.println("Nume lipsă pentru comandă.");
                    return;
                }
                String targetName = cmdParts[1].trim();

                // Căutăm studentul
                Student original = null;
                for (Student s : studenti) {
                    if (s.getNume().equals(targetName)) {
                        original = s;
                        break;
                    }
                }

                if (original != null) {
                    Student clona = null;
                    if (command.equals("SHALLOW")) {
                        clona = original.shallowClone();
                    } else {
                        clona = original.deepClone();
                    }

                    // Modificăm orașul clonei la "MODIFICAT"
                    clona.getAdresa().setOras("MODIFICAT");

                    // Afișăm originalul și clona
                    System.out.println("Original: " + original);
                    System.out.println("Clona: " + clona);
                }
                break;

            default:
                System.out.println("Comandă necunoscută.");
        }
    }
}