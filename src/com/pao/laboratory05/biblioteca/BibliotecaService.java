package com.pao.laboratory05.biblioteca;

import com.pao.laboratory05.playlist.Song;

import java.util.Arrays;
import java.util.Comparator;

public class BibliotecaService {

    void addCarte(Carte carte){
        Carte[] new_carti = new Carte[carti.length + 1];
        System.arraycopy(carti, 0, new_carti, 0, carti.length);
        new_carti[carti.length] = carte;
        carti = new_carti;
        System.out.println("Cartea '" + carte.getTitlu() + "' a fost adaugata cu succes!");
    }
    void listSortedByRating(){
        Carte[] clone = new Carte[carti.length];
        System.arraycopy(carti, 0, clone, 0, carti.length);
        Arrays.sort(clone);
        for(Carte c : clone){
            System.out.println(c);
        }
    }
    void listSortedBy(Comparator<Carte> comparator){
        Carte[] clone = new Carte[carti.length];
        System.arraycopy(carti, 0, clone, 0, carti.length);
        Arrays.sort(clone, comparator);
        for(Carte c : clone){
            System.out.println(c);
        }
    }
    private BibliotecaService(){
        carti = new Carte[0];
    }
    public static BibliotecaService getInstance(){
        if(instance == null){
            instance = new BibliotecaService();
        }
        return instance;
    }
    static private BibliotecaService instance = null;
    private Carte[] carti;

}
