package com.practice.solidandpatterns.behavioral.iterator.playlist;

import java.util.List;

public class GenreFilterIterator implements SongIterator {
  private final List<Song> songs;
  private final String targetGenre;
  private int currentIndex = 0;

  public GenreFilterIterator(List<Song> songs, String targetGenre) {
    this.songs = songs;
    this.targetGenre = targetGenre;
  }

  @Override
  public boolean hasNext() {
    while (currentIndex < songs.size()) {
      if (songs.get(currentIndex).getGenre().equals(targetGenre)) {
        return true;
      }
      currentIndex++;
    }
    return false;
  }

  @Override
  public Song next() {
    if (!hasNext()) {
      throw new java.util.NoSuchElementException("No more songs in genre: " + targetGenre);
    }
    return songs.get(currentIndex++);
  }
}
