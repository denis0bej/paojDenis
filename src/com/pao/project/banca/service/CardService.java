package com.pao.project.banca.service;

import com.pao.project.banca.model.Card;
import java.util.HashMap;
import java.util.Map;

public class CardService {
    private static CardService instance;

    // Folosim un Map pentru a indexa cardurile după numărul lor (Cerința 2.2)
    private Map<String, Card> carduriEmise = new HashMap<>();

    private CardService() {
        // Constructor privat pentru Singleton
    }

    public static CardService getInstance() {
        if (instance == null) {
            instance = new CardService();
        }
        return instance;
    }

    // 1. Adaugă (Emite card)
    public void emiteCard(String numarCard, String ibanAtasat) {
        if (carduriEmise.containsKey(numarCard)) {
            System.out.println("Eroare: Cardul cu numărul " + numarCard + " există deja!");
            return;
        }
        Card cardNou = new Card(numarCard, ibanAtasat);
        carduriEmise.put(numarCard, cardNou);
        System.out.println("Card emis cu succes: " + numarCard + " pentru contul " + ibanAtasat);
    }

    // 2. Caută după ID (Număr card)
    public Card gasesteCard(String numarCard) {
        if (!carduriEmise.containsKey(numarCard)) {
            throw new RuntimeException("Cardul cu numărul " + numarCard + " nu a fost găsit!");
        }
        return carduriEmise.get(numarCard);
    }

    // 3. Șterge (Anulează/Blochează card)
    public void anuleazaCard(String numarCard) {
        if (carduriEmise.containsKey(numarCard)) {
            carduriEmise.remove(numarCard);
            System.out.println("Cardul " + numarCard + " a fost anulat/șters din sistem.");
        } else {
            System.out.println("Eroare la anulare: Cardul " + numarCard + " nu există!");
        }
    }

    // 4. Listează toate
    public void afiseazaToateCardurile() {
        System.out.println("--- Lista Carduri Emise ---");
        if (carduriEmise.isEmpty()) {
            System.out.println("Nu există carduri emise.");
        } else {
            for (Card c : carduriEmise.values()) {
                System.out.println(c);
            }
        }
    }
}