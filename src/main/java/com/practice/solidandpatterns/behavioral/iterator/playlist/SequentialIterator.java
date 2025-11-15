package com.practice.solidandpatterns.behavioral.iterator.playlist;

import java.util.List;

public class SequentialIterator implements SongIterator {
  private final List<Song> songs;
  private int index = 0;

  public SequentialIterator(List<Song> songs) {
    this.songs = songs;
  }

  @Override
  public boolean hasNext() {
    return index < songs.size();
  }

  @Override
  public Song next() {
    if (!hasNext()) {
      throw new java.util.NoSuchElementException("No more songs in sequential iterator.");
    }
    return songs.get(index++);
  }
}
