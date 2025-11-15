package com.practice.solidandpatterns.behavioral.iterator.playlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleIterator implements SongIterator {
  private final List<Song> shuffledSongs;
  private int index = 0;

  public ShuffleIterator(List<Song> originalSongs) {
    this.shuffledSongs = new ArrayList<>(originalSongs);
    Collections.shuffle(shuffledSongs);
  }

  @Override
  public boolean hasNext() {
    return index < shuffledSongs.size();
  }

  @Override
  public Song next() {
    if (!hasNext()) {
      throw new java.util.NoSuchElementException("No more songs in shuffled iterator.");
    }
    return shuffledSongs.get(index++);
  }
}
