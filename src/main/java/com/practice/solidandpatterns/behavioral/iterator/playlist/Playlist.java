package com.practice.solidandpatterns.behavioral.iterator.playlist;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
  private final List<Song> songs = new ArrayList<>();

  public void addSong(Song song) {
    songs.add(song);
  }

  public int size() {
    return songs.size();
  }

  public SongIterator sequentialIterator() {
    return new SequentialIterator(songs);
  }

  public SongIterator shuffleIterator() {
    return new ShuffleIterator(songs);
  }

  public SongIterator genreIterator(String genre) {
    return new GenreFilterIterator(songs, genre);
  }
}
