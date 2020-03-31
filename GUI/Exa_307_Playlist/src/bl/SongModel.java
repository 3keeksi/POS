/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Song;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Denis Imeri
 */
public class SongModel extends AbstractListModel {

    Comparator<Song> title = Comparator.comparing(Song::getTitle);
    Comparator<Song> length = Comparator.comparing(Song::getLength);
    private int completeLength = 0;

    List<Song> songs = new ArrayList<>();
    List<Song> filteredSongs = new ArrayList<>();

    public SongModel() {
    }

    public void getPlaylistData() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("songs.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray songList = (JSONArray) obj;

            //Iterate over employee array
            songList.forEach(song -> addSong(parseSong((JSONObject) song)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Song parseSong(JSONObject song) {
        //Get employee first name
        String title = (String) song.get("title");
        

        //Get employee last name
        String artist = (String) song.get("artist");
        

        //Get employee website name
        long temp = (long) song.get("length");
        int length = Integer.parseInt(Long.toString(temp));
        return new Song(title, artist, length);
    }

    public void addSong(Song song) {
        if (!songs.contains(song)) {
            songs.add(song);
            filter("");
            completeLength+=song.getLength();
        }
    }

    public void addSongs(List<Song> toAdd) {
        for (Song song : toAdd) {
            if (!songs.contains(song)) {
                songs.add(song);
                completeLength+=song.getLength();
            }
        }
        filter("");
    }

    public void clear() {
        songs.clear();
        completeLength = 0;
        filter("");
    }

    public void removeSong(Song toRemove) {
        songs.remove(toRemove);
        completeLength-=toRemove.getLength();
        filter("");
    }

    public void removeSongs(List<Song> toRemove) {
        songs.removeAll(toRemove);
        for(Song song : toRemove) {
            completeLength-=song.getLength();
        }
        filter("");
    }

    public void sort(String type) {
        if (type.equals("titel")) {
            Collections.sort(songs, title);
        } else if (type.equals("laenge")) {
            Collections.sort(songs, length);
        } else {
            songs.addAll(songs);
        }
        filter("");
    }

    public void filter(String interpret) {
        filteredSongs.clear();

        if (interpret.equals("")) {
            filteredSongs.addAll(songs);
        } else {
            for (Song song : songs) {
                if (song.getArtist().equals(interpret)) {
                    filteredSongs.add(song);
                }
            }
        }
        this.fireContentsChanged(this, 0, filteredSongs.size());
    }

    public int getCompleteLength() {
        return completeLength;
    }

    @Override
    public int getSize() {
        return filteredSongs.size();
    }

    @Override
    public Object getElementAt(int index) {
        return filteredSongs.get(index);
    }

}
