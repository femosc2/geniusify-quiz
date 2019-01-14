import com.fasterxml.jackson.databind.ObjectMapper;


import static spark.Spark.*;

/**
 * Mainclass that starts the backend and starts listening to our endpoints on port 9022.
 * Author: Mikael Lindfors
 */
public class Main {

    public static void main(String[] args) {
        final ApiConnect apiConnect = new ApiConnect();
        Token token = apiConnect.getTokenFromSpotify();
        apiConnect.setToken(token);
        Preload preload = new Preload(token, apiConnect);
        port(9022);
        staticFiles.location("/public");
        ObjectMapper objectMapper = new ObjectMapper();
/**
 * Endpoint for christmas songs. When a song is fetched a new Thread in preloader will add new tracks to
 * the queue with christmas songs.
 */
        get("/genre/christmas", (req, res) -> {
            res.type("application/json");
            res.header("Access-Control-Allow-Origin", "*");
            Preload preLoader = preload.getPreloader();
            Track track = preLoader.getTrackFromQueue("christmas");
            preLoader.CreateQueueUpdater(preload, "christmas");
            return objectMapper.writeValueAsString(track);
        });
        /**
         * Endpoint for rock songs. When a song is fetched a new Thread in preloader will add new tracks to
         * the queue with rock songs.
         */
        get("/genre/rock", (req, res) -> {
            res.type("application/json");
            res.header("Access-Control-Allow-Origin", "*");
            Preload preLoader = preload.getPreloader();
            Track track = preLoader.getTrackFromQueue("rock");
            preLoader.CreateQueueUpdater(preload, "rock");
            return objectMapper.writeValueAsString(track);
        });
        /**
         * Endpoint for pop songs. When a song is fetched a new Thread in preloader will add new tracks to
         * the queue with pop songs.
         */
        get("/genre/pop", (req, res) -> {
            res.type("application/json");
            res.header("Access-Control-Allow-Origin", "*");
            Preload preLoader = preload.getPreloader();
            Track track = preLoader.getTrackFromQueue("pop");
            preLoader.CreateQueueUpdater(preload, "pop");
            return objectMapper.writeValueAsString(track);
        });
        /**
         * Endpoint for rap songs. When a song is fetched a new Thread in preloader will add new tracks to
         * the queue with rap songs.
         */
        get("/genre/rap", (req, res) -> {
            res.type("application/json");
            res.header("Access-Control-Allow-Origin", "*");
            Preload preLoader = preload.getPreloader();
            Track track = preLoader.getTrackFromQueue("rap");
            preLoader.CreateQueueUpdater(preload, "rap");
            return objectMapper.writeValueAsString(track);
        });
    }

}