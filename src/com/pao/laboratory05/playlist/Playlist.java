package com.pao.laboratory05.playlist;

import java.util.Arrays;

public class Playlist {
    private String name;
    private Song[] songs;

    public String getName(){
        return name;
    }
    public Song[] getSongs(){
        return songs;
    }
    public Playlist(String name){
        this.name = name;
        this.songs = new Song[0];
    }
    void addSong(Song song){
        Song[] new_songs = new Song[songs.length + 1];
        System.arraycopy(songs, 0, new_songs, 0, songs.length);
        new_songs[songs.length] = song;
        songs = new_songs;
    }
    void printSortedByTitle(){
        Song[] clone = new Song[songs.length];
        System.arraycopy(songs, 0, clone, 0, songs.length);
        Arrays.sort(clone);
        for(Song s : clone){
            System.out.println(s);
        }
    }
    void printSortedByDuration(){
        Song[] clone = new Song[songs.length];
        System.arraycopy(songs, 0, clone, 0, songs.length);
        Arrays.sort(clone, new SongDurationComparator());
        for(Song s : clone){
            System.out.println(s);
        }
    }
    int getTotalDuration(){
        int total = 0;
        for(Song s : songs){
            total += s.durationSeconds();
        }
        return total;
    }
}
