import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

public class PlayList {
    LinkedList<Track> tracks;

    public PlayList() {
        tracks = new LinkedList<Track>();
    }

    public LinkedList<Track> getTracksList() {
        return tracks;
    }
    public void setTracksList(LinkedList<Track> tracks) {
        this.tracks = tracks;
    }
    public void addTrack(Track track) {
        this.tracks.add(track);
    }
    public Track getTrack(int index) {
        return tracks.get(index);
    }
}
