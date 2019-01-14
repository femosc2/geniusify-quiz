

import java.util.LinkedList;

/**
 * PlayList class that handles a spotify playlist.
 * Author: Mikael Lindfors
 */
public class PlayList {
    LinkedList<Track> tracks;

    /**
     * Constructor that initialize the linkedList with tracks
     */
    public PlayList() {
        tracks = new LinkedList<Track>();
    }

    /**
     * Method that returns the linkedList with tracks
     * @return LinkedList<Track> with all the tracks
     */
    public LinkedList<Track> getTracksList() {
        return tracks;
    }

    /**
     * Method that sets the linkedList with tracks
     * @param tracks LinkedList<Track> with all the tracks.
     */
    public void setTracksList(LinkedList<Track> tracks) {
        this.tracks = tracks;
    }

    /**
     * Method that adds a single track to the trackList.
     * @param track - Track object with a single track.
     */
    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    /**
     * Method that returns the track of a specified index.
     * @param index index of the track in the tracklist.
     * @return Track object with a single track
     */
    public Track getTrack(int index) {
        return tracks.get(index);
    }
}
