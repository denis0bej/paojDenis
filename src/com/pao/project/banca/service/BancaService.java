package com.pao.project.banca.service;

import com.pao.project.banca.exception.*;
import com.pao.project.banca.model.*;

import java.util.*;

public class BancaService {
    private static BancaService instance;

    // Colectie sortata pentru clienti (Cerinta 2.2)
    private Set<Client> clienti = new TreeSet<>();

    // Map pentru indexare conturi dupa IBAN (Cerinta 2.2)
    private Map<String, ContBancar> conturi = new HashMap<>();

    private List<Card> carduriEmise = new ArrayList<>();

    private BancaService() {}

    public static BancaService getInstance() {
        if (instance == null) instance = new BancaService();
        return instance;
    }

    public void adaugaClient(Client client) {
        clienti.add(client);
    }

    public void deschideCont(ContBancar cont) {
        conturi.put(cont.getIban(), cont);
    }

    public ContBancar gasesteCont(String iban) {
        if (!conturi.containsKey(iban)) {
            throw new ContInexistentException("Contul cu IBAN " + iban + " nu a fost gasit!");
        }
        return conturi.get(iban);
    }

    public void efectueazaTransfer(String ibanSursa, String ibanDestinatie, double suma) throws Exception {
        ContBancar sursa = gasesteCont(ibanSursa);
        ContBancar destinatie = gasesteCont(ibanDestinatie);

        if (sursa.getSold() < suma) {
            throw new FonduriInsuficienteException("Transfer esuat: sold insuficient!");
        }

        sursa.retragere(suma);
        destinatie.depunere(suma);

        System.out.println("Transfer realizat cu succes: " + suma + " RON");
    }

    public void afiseazaTotiClientii() {
        System.out.println("--- Lista Clienti (Sortati alfabetic) ---");
        for (Client c : clienti) {
            System.out.println(c);
        }
    }

    public List<ContBancar> getConturiClient(String cnp) {
        List<ContBancar> rezultat = new ArrayList<>();
        for (ContBancar cont : conturi.values()) {
            if (cont.getTitular().getCnp().equals(cnp)) {
                rezultat.add(cont);
            }
        }
        return rezultat;
    }

    public void inchideCont(String iban) {
        if (!conturi.containsKey(iban)) {
            throw new ContInexistentException("Eroare la ștergere: Contul cu IBAN " + iban + " nu există!");
        }
        conturi.remove(iban);
        System.out.println("Contul " + iban + " a fost închis și șters din sistem cu succes.");
    }

    public void stergeClient(String cnp) {
        Client clientDeSters = null;
        for (Client c : clienti) {
            if (c.getCnp().equals(cnp)) {
                clientDeSters = c;
                break;
            }
        }

        if (clientDeSters != null) {
            clienti.remove(clientDeSters);
            System.out.println("Clientul cu CNP " + cnp + " a fost șters din sistem.");

            // Opțional: Ștergem și conturile asociate acestui client pentru a păstra consistența
            conturi.entrySet().removeIf(entry -> entry.getValue().getTitular().getCnp().equals(cnp));
        } else {
            System.out.println("Eroare la ștergere: Clientul cu CNP " + cnp + " nu a fost găsit!");
        }
    }
}