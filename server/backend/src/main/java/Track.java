public class Track {

    private String name;
    private String artist;
    private String imageUrl;
    private String lyrics;
    private String[] words;

    public Track(String name, String artist, String imageUrl) {
        this.name = name;
        this.artist = artist;
        this.imageUrl = imageUrl;

    }
    public void setWords(String[] words) {
        this.words = words;
    }
    public String[] getWords() {
        return words;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
    public String getLyrics() {
        return this.lyrics;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
