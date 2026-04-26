package com.pao.project.banca.model;

import java.time.LocalDate;
import java.util.Objects;

public class Card {
    private String numarCard;
    private String ibanAtasat;
    private LocalDate dataExpirare;

    public Card(String numarCard, String ibanAtasat) {
        this.numarCard = numarCard;
        this.ibanAtasat = ibanAtasat;
        this.dataExpirare = LocalDate.now().plusYears(4);
    }

    @Override
    public String toString() {
        return "Card{" + "numar='" + numarCard + '\'' + ", expira=" + dataExpirare + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(numarCard, card.numarCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numarCard);
    }
}