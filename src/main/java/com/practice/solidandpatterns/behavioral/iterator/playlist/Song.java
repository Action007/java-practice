package com.practice.solidandpatterns.behavioral.iterator.playlist;

public class Song {
  private final String title;
  private final String artist;
  private final String genre;

  public Song(String title, String artist, String genre) {
    this.title = title;
    this.artist = artist;
    this.genre = genre;
  }

  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

  public String getGenre() {
    return genre;
  }

  @Override
  public String toString() {
    return title + " by " + artist + " [" + genre + "]";
  }
}
