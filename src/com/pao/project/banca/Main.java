package com.pao.project.banca;

import com.pao.project.banca.model.*;
import com.pao.project.banca.service.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BancaService banca = BancaService.getInstance();
        CardService cardService = CardService.getInstance();

        // 1. Adăugarea unui client nou în sistem.
        System.out.println("--- 1. Adăugarea unui client nou în sistem ---");
        Client c1 = new Client("123", "Ionescu Andrei", "Bucuresti", "0722");
        Client c2 = new Client("456", "Popescu Maria", "Cluj", "0733");
        banca.adaugaClient(c2);
        banca.adaugaClient(c1);
        System.out.println("Clienții Ionescu Andrei și Popescu Maria au fost adăugați.\n");

        // 2. Deschiderea unui cont curent pentru un client.
        System.out.println("--- 2. Deschiderea unui cont curent pentru un client ---");
        ContBancar contCurent = new ContCurent("RO01BANC", c1, 1000);
        ContBancar contPremium = new ContCurentPremium("RO02BANC", c2);
        banca.deschideCont(contCurent);
        banca.deschideCont(contPremium);
        System.out.println("Conturile curente au fost deschise cu succes.\n");

        // 3. Deschiderea unui cont de economii pentru un client.
        System.out.println("--- 3. Deschiderea unui cont de economii pentru un client ---");
        ContBancar contEconomii = new ContEconomii("RO03BANC", c1, 0.05); // Dobândă de 5%
        banca.deschideCont(contEconomii);
        System.out.println("Contul de economii a fost deschis pentru Ionescu Andrei.\n");

        // 4. Emiterea unui card atașat unui anumit cont bancar.
        System.out.println("--- 4. Emiterea unui card atașat unui anumit cont bancar ---");
        cardService.emiteCard("1111-2222-3333-4444", "RO01BANC");
        cardService.emiteCard("5555-6666-7777-8888", "RO01BANC");
        System.out.println("Cardurile au fost emise pentru contul RO01BANC.\n");

        // 5. Efectuarea unei depuneri de numerar într-un cont.
        System.out.println("--- 5. Efectuarea unei depuneri de numerar într-un cont ---");
        contCurent.depunere(5000);
        System.out.println("S-au depus 5000 RON în contul RO01BANC. Noul sold: " + contCurent.getSold() + "\n");

        // 6. Efectuarea unui transfer bancar între două conturi (pe baza IBAN-ului).
        System.out.println("--- 6. Efectuarea unui transfer bancar între două conturi ---");
        try {
            banca.efectueazaTransfer("RO01BANC", "RO02BANC", 1200);
            System.out.println("Transfer de 1200 RON efectuat cu succes între RO01BANC și RO02BANC.\n");
        } catch (Exception e) {
            System.out.println("Eroare la transfer: " + e.getMessage() + "\n");
        }

        // 7. Căutarea unui cont specific folosind IBAN-ul.
        System.out.println("--- 7. Căutarea unui cont specific folosind IBAN-ul ---");
        try {
            ContBancar cautat = banca.gasesteCont("RO01BANC");
            System.out.println("Cont găsit: " + cautat.getIban() + " | Sold: " + cautat.getSold() + "\n");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + "\n");
        }

        // 8. Afișarea tuturor clienților băncii, sortați alfabetic după nume.
        System.out.println("--- 8. Afișarea tuturor clienților băncii, sortați alfabetic ---");
        banca.afiseazaTotiClientii();
        System.out.println();

        // 9. Listarea tuturor conturilor deținute de un anumit client.
        System.out.println("--- 9. Listarea tuturor conturilor deținute de un anumit client ---");
        System.out.println("Conturile clientului Ionescu Andrei (CNP: 123):");
        List<ContBancar> conturiIonescu = banca.getConturiClient("123");
        conturiIonescu.forEach(c -> System.out.println("- " + c.getIban() + " | Sold: " + c.getSold()));
        System.out.println();

        // 10. Afișarea istoricului de tranzacții (extrasului de cont) pentru un anumit cont.
        System.out.println("--- 10. Afișarea istoricului de tranzacții (extrasului de cont) ---");
        System.out.println("Extras de cont pentru RO01BANC:");
        contCurent.getIstoricTranzactii().forEach(System.out::println);
        System.out.println();


        System.out.println("=== DEMONSTRARE SERVICII ===\n");

        System.out.println("--- Căutare card specific ---");
        try {
            Card cardGasit = cardService.gasesteCard("1111-2222-3333-4444");
            System.out.println("Am căutat și găsit cardul: " + cardGasit);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n--- Listare toate cardurile din sistem ---");
        cardService.afiseazaToateCardurile();

        System.out.println("\n--- Testare operații de ștergere (Cont, Client, Card) ---");
        System.out.println("Închidem contul RO02BANC...");
        banca.inchideCont("RO02BANC");

        System.out.println("Ștergem clientul Popescu Maria (CNP: 456)...");
        banca.stergeClient("456");

        System.out.println("Anulăm cardul 5555-6666-7777-8888...");
        cardService.anuleazaCard("5555-6666-7777-8888");

        System.out.println("\n--- Verificare finală după ștergeri ---");
        banca.afiseazaTotiClientii();
        cardService.afiseazaToateCardurile();
    }
}