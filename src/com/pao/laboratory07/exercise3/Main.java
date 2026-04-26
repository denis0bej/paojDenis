package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Citirea numărului de comenzi
        int n = 0;
        try {
            if (scanner.hasNextLine()) {
                n = Integer.parseInt(scanner.nextLine().trim());
            }
        } catch (NumberFormatException e) {
            System.err.println("Număr invalid de comenzi.");
            return;
        }

        List<Comanda> comenzi = new ArrayList<>();

        // Parsarea inputului și popularea listei
        for (int i = 0; i < n; i++) {
            String linie = scanner.nextLine().trim();
            if (linie.isEmpty()) continue;

            String[] tokens = linie.split(" ");
            String tip = tokens[0];

            try {
                switch (tip) {
                    case "STANDARD" -> {
                        if (tokens.length < 4) throw new InvalidCommandException("Parametri lipsă pentru STANDARD");
                        comenzi.add(new ComandaStandard(tokens[1], Double.parseDouble(tokens[2]), tokens[3]));
                    }
                    case "DISCOUNTED" -> {
                        if (tokens.length < 5) throw new InvalidCommandException("Parametri lipsă pentru DISCOUNTED");
                        comenzi.add(new ComandaRedusa(tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), tokens[4]));
                    }
                    case "GIFT" -> {
                        if (tokens.length < 3) throw new InvalidCommandException("Parametri lipsă pentru GIFT");
                        comenzi.add(new ComandaGratuita(tokens[1], tokens[2]));
                    }
                    default -> throw new InvalidCommandException("Tip de comandă necunoscut: " + tip);
                }
            } catch (Exception e) {
                System.err.println("Eroare la parsarea comenzii: " + e.getMessage());
            }
        }

        // 1. Afișare comenzi inițiale (cu status [PLACED])
        comenzi.forEach(c -> System.out.println(c.format(true)));

        // 2. Procesare operații de Stream API
        while (scanner.hasNextLine()) {
            String comanda = scanner.nextLine().trim();
            if (comanda.isEmpty()) continue;

            if (comanda.equals("QUIT")) {
                break;
            } else if (comanda.equals("STATS")) {
                handleStats(comenzi);
            } else if (comanda.startsWith("FILTER")) {
                try {
                    double threshold = Double.parseDouble(comanda.split(" ")[1]);
                    handleFilter(comenzi, threshold);
                } catch (Exception e) {
                    System.err.println("Eroare la argumentul FILTER.");
                }
            } else if (comanda.equals("SORT")) {
                handleSort(comenzi);
            } else if (comanda.equals("SPECIAL")) {
                handleSpecial(comenzi);
            } else {
                System.out.println("Operațiune necunoscută: " + comanda);
            }
        }

        scanner.close();
    }

    // -- Metode de tratare a operațiilor Stream API --

    private static void handleStats(List<Comanda> comenzi) {
        System.out.println("--- STATS ---");

        // pattern matching cu instanceof în lamba
        Map<String, Double> mediile = comenzi.stream()
                .collect(Collectors.groupingBy(
                        c -> {
                            if (c instanceof ComandaStandard) return "STANDARD";
                            if (c instanceof ComandaRedusa) return "DISCOUNTED";
                            if (c instanceof ComandaGratuita) return "GIFT";
                            return "UNKNOWN";
                        },
                        Collectors.averagingDouble(Comanda::calculPretFinal)
                ));

        // Asigurăm afișarea ordonată a tipurilor, chiar dacă mediile sunt 0, pentru a respecta formatul
        String[] tipuri = {"STANDARD", "DISCOUNTED", "GIFT"};
        for (String tip : tipuri) {
            double medie = mediile.getOrDefault(tip, 0.0);
            System.out.printf(Locale.US, "%s: medie = %.2f lei\n", tip, medie);
        }
    }

    private static void handleFilter(List<Comanda> comenzi, double threshold) {
        System.out.printf(Locale.US, "--- FILTER (>= %.2f) ---\n", threshold);

        List<Comanda> filtered = comenzi.stream()
                .filter(c -> c.calculPretFinal() >= threshold)
                .toList();

        filtered.forEach(c -> System.out.println(c.format(false)));
    }

    private static void handleSort(List<Comanda> comenzi) {
        System.out.println("--- SORT (by client, then by pret) ---");

        List<Comanda> sorted = comenzi.stream()
                .sorted(Comparator.comparing(Comanda::getClient)
                        .thenComparing(Comanda::calculPretFinal))
                .toList();

        sorted.forEach(c -> System.out.println(c.format(false)));
    }

    private static void handleSpecial(List<Comanda> comenzi) {
        System.out.println("--- SPECIAL (discount > 15%) ---");

        List<Comanda> special = comenzi.stream()
                .filter(c -> c instanceof ComandaRedusa r && r.getDiscountProcent() > 15)
                .toList();

        special.forEach(c -> System.out.println(c.format(false)));
    }
}