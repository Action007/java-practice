package com.practice.solidandpatterns.behavioral.iterator.playlist;

public class MainApp {
  public static void main(String[] args) {
    Playlist playlist = new Playlist();
    playlist.addSong(new Song("The Look of Love", "Chris Botti", "Jazz"));
    playlist.addSong(new Song("A Night in Tunisia", "Miles Davis", "Jazz"));
    playlist.addSong(new Song("La Vie en Rose", "Louis Armstrong", "Jazz"));
    playlist.addSong(new Song("Bohemian Rhapsody", "Queen", "Rock"));
    playlist.addSong(new Song("Billie Jean", "Michael Jackson", "Pop"));

    System.out.println("=== Sequential ===");
    SongIterator seq = playlist.sequentialIterator();
    while (seq.hasNext()) {
      System.out.println(seq.next());
    }

    System.out.println("=== Shuffle (Run 1) ===");
    SongIterator shuffle1 = playlist.shuffleIterator();
    while (shuffle1.hasNext()) {
      System.out.println(shuffle1.next());
    }

    System.out.println("=== Shuffle (Run 2) ===");
    SongIterator shuffle2 = playlist.shuffleIterator();
    while (shuffle2.hasNext()) {
      System.out.println(shuffle2.next());
    }

    System.out.println("=== Genre: Jazz ===");
    SongIterator jazz = playlist.genreIterator("Jazz");
    while (jazz.hasNext()) {
      System.out.println(jazz.next());
    }

    System.out.println("=== Independent Iterations ===");
    SongIterator it1 = playlist.sequentialIterator();
    SongIterator it2 = playlist.shuffleIterator();
    System.out.println("First (seq): " + it1.next());
    System.out.println("First (shuffle): " + it2.next());
    System.out.println("Second (seq): " + it1.next());
  }
}
