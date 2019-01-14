
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;
import org.apache.tomcat.util.codec.binary.Base64;
import com.fasterxml.jackson.databind.*;

/**
 * Class used for connecting to external api's using okhttp3 as http-client
 * Author: Mikael Lindfors
 */

public class ApiConnect {
    private Gson gson;
    private Token token;
    private int counter = 0;

    /**
     * Constructor that initialize the Gson object.
     */
    ApiConnect() {
        gson = new Gson();
    }

    /**
     * Method that returns the Spotify authorization token.
     *
     * @return token - token object
     */
    public Token getToken() {
        return token;
    }

    /**
     * Method that sets the Spotify authorization token.
     *
     * @param token - token object.
     */
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     * Method that crops parts of a Songs name if it consists of special characters
     * to make it easier for Apiseeds to find the song.
     *
     * @param songName - String with the name of the song.
     * @return String with the new name of the song.
     */
    public String cropSongName(String songName) {
        String result = "";
        if (songName.contains("(")) {
            System.out.println("Modiferar låttitel från: " + songName);
            result = songName.substring(0, songName.indexOf('('));
            System.out.println("till: " + result);
        } else if (songName.contains("-")) {
            System.out.println("Modifierar låttitel från: " + songName);
            result = songName.substring(0, songName.indexOf('-'));
            System.out.println("till:" + result);
        } else {
            return songName;
        }
        return result;
    }

    /**
     * Method that fetch lyrics for a track from apiSeeds.
     * Using okhttp3 client for communication.
     *
     * @param artist String - name of the artist.
     * @param song   String - name of the song
     * @return String lyrics for the track.
     */
    public String fetchLyrics(String artist, String song) {
        counter++;
        song = cropSongName(song);
        OkHttpClient client = new OkHttpClient();
        String subscriptionKey = "4338ddc4a9814dd58342519623a06b5d";
        String url = "https://orion.apiseeds.com/api/music/lyric/" + artist + "/" + song + "?apikey=";
        String apikey = "KgqMkE8CSCBRGZJS69yXTtkHlI2YM4LGlGGTvHDVK5lKqTtHK5yYO5h8Mkq2IIkg";
        Request request = new Request.Builder()
                .url(url + apikey)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
                .addHeader("Content-type", "application/json").build();
        try {
            System.out.println("Hämtar text för: " + artist + " - " + song);
            Response response = client.newCall(request).execute();
            LyricsResult res = gson.fromJson(response.body().string(), LyricsResult.class);
            System.out.println(counter);
            return res.getResult().getTrack().getText();
        } catch (Exception e) {
            return "Låttext hittas ej för: " + artist + " - " + song;
        }
    }

    /**
     * Method that fetch an authorization token from spotify.
     * Using okhttp3 client for communication.
     * @return Token object
     */
    public Token getTokenFromSpotify() {
        OkHttpClient client = new OkHttpClient();
        String client_id = "edb6f939e332455483f8e58066d110aa";
        String client_secret = "cadb6cd3c34f4863a85ba3b246857d2a";
        String url = "https://accounts.spotify.com/api/token";
        String identification = client_id + ":" + client_secret;
        String idEncoded = new String(Base64.encodeBase64(identification.getBytes()));
        RequestBody formBody = new FormBody.Builder().add("grant_type", "client_credentials").build();
        Request request = new Request.Builder()
                .url(url).post(formBody)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Basic " + idEncoded).build();
        try {
            Response response = client.newCall(request).execute();
            Token token = gson.fromJson(response.body().string(), Token.class);
            System.out.println(token.getAccess_token() + "Expires om = " + token.getExpires_in());
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that fetch a playlist from spotify.
     * Using okhttp3 client for communication.
     * @param genre String with specified genre
     * @return PlayList object
     */
    public PlayList getPlayList(String genre) {
        String url = "";
        PlayList result = new PlayList();
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        switch (genre) {
            case "christmas": {
                url = "https://api.spotify.com/v1/playlists/5OP7itTh52BMfZS1DJrdlv/tracks";
                break;
            }
            case "rock": {
                url = "https://api.spotify.com/v1/playlists/3qu74M0PqlkSV76f98aqTd/tracks";
                break;
            }
            case "pop": {
                url = "https://api.spotify.com/v1/playlists/3ZgmfR6lsnCwdffZUan8EA/tracks";
                break;
            }
            case "rap": {
                url = "https://api.spotify.com/v1/playlists/21sgjLGbnEgNMTpjnaO2b6/tracks";
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + this.getToken().getAccess_token())
                .build();
        try {
            Response response = client.newCall(request).execute();
            String resultBody = response.body().string();
//            JsonNode node = objectMapper.readValue(resultBody, JsonNode.class);
            JsonNode arrayTest = new ObjectMapper().readTree(resultBody).get("items");
            for (JsonNode items : arrayTest) {
                JsonNode track = items.get("track");
                JsonNode trackNameUnChopped = track.get("name");
                JsonNode album = track.get("album");
                JsonNode imageArray = album.get("images");
                JsonNode imageURLarray = imageArray.get(1);
                JsonNode imageURL = imageURLarray.get("url");
                JsonNode artistArray = track.get("artists");
                JsonNode artist = artistArray.get(0);
                JsonNode artistName = artist.get("name");
                String trackName = trackNameUnChopped.asText();
                if (trackName.indexOf("-") > -1) {
                    trackName = trackName.substring(0, trackName.indexOf("-"));
                }
                result.addTrack(new Track(trackName, artistName.asText(), imageURL.asText()));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
