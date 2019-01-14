import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class that handles the resulting lyrics from apiseeds.
 * Author: Mikael Lindfors
 */
public class LyricsResult {

    @SerializedName("result")
    @Expose
    private Result result;

    /**
     * Method that returns the result object.
     *
     * @return Result object
     */
    public Result getResult() {
        return result;
    }

    /**
     * Method that sets the result.
     *
     * @param result Result object
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * Internal Result class.
     * Author: Mikael Lindfors
     */

    public class Result {
        @SerializedName("artist")
        @Expose
        private Artist artist;
        @SerializedName("track")
        @Expose
        private Track track;

        /**
         * Method that returns the artists object
         *
         * @return artist object
         */
        public Artist getArtist() {
            return artist;
        }

        /**
         * Method that sets the artist object
         *
         * @param artist artist object
         */
        public void setArtist(Artist artist) {
            this.artist = artist;
        }

        /**
         * Method that returns the track
         *
         * @return Track object
         */
        public Track getTrack() {
            return track;
        }

        /**
         * Method that sets the track
         *
         * @param track Track object
         */
        public void setTrack(Track track) {
            this.track = track;
        }

    }

    /**
     * Internal class that handles the artists
     * Athor: Mikael Lindfors
     */
    public class Artist {
        @SerializedName("name")
        @Expose
        private String name;

        /**
         * Method that gets the name of the artists
         *
         * @return String name
         */
        public String getName() {
            return name;
        }

        /**
         * Method that sets the name of the artists
         *
         * @param name STring name
         */
        public void setName(String name) {
            this.name = name;
        }

    }

    /**
     * Internal class that handles the track
     */
    public class Track {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("text")
        @Expose
        private String text;

        /**
         * Method that sets the name of the track
         * @return return String name of the track
         */
        public String getName() {
            return name;
        }

        /**
         * Method that sets the name of the track
         * @param name String name of the track
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Method that returns the lyrics of the track
         * @return String lyrics of the track
         */
        public String getText() {
            return text;
        }

        /**
         * Method that sets the lyrics of the track
         * @param text String lyrics of the track
         */
        public void setText(String text) {
            this.text = text;
        }
    }
}
