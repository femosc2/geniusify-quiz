/**
 * Class that handles each Track in a playlist.
 * Author: Mikael Lindfors
 */
public class Track {

    private String name;
    private String artist;
    private String imageUrl;
    private String lyrics;
    private String[] words;

    /**
     * Constructor that sets trackname, artist and image-url
     * @param name String name of the track
     * @param artist String name of the artist
     * @param imageUrl String url to the album-cover.
     */
    public Track(String name, String artist, String imageUrl) {
        this.name = name;
        this.artist = artist;
        this.imageUrl = imageUrl;

    }

    /**
     * Method that sets the words to has been removed from the lyrics.
     * @param words String array with the removed words.
     */
    public void setWords(String[] words) {
        this.words = words;
    }

    /**
     * Method that fetches the words that has been removed from the lyrics.
     * @return String array with the removed words-
     */
    public String[] getWords() {
        return words;
    }

    /**
     * Method that sets lyrics for a track.
     * @param lyrics String with the lyrics for the track.
     */
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    /**
     * Method that gets the lyrics for a track.
     * @return String with the lyrics for the track.
     */
    public String getLyrics() {
        return this.lyrics;
    }

    /**
     * Method that gets the track name
     * @return String track name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method that sets the name of the track
     * @param name String name of the track.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that gets the artist name of the track
     * @return String artist name,
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Method that sets the artist name
     * @param artist String artist name.
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Method that gets the image-url for the track
     * @return String url for the image.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Method that sets the image-url for the track
     * @param imageUrl String with the url for the image.
     */

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
