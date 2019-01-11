import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        final ApiConnect apiConnect = new ApiConnect();
        Token token = apiConnect.getTokenFromSpotify();
        apiConnect.setToken(token);
        Preload preload = new Preload(token,apiConnect);
//        preload.loadSongs();
//        preload.fillQueue("christmas");
//        preload.fillQueue("rock");
//        preload.fillQueue("pop");
//        preload.fillQueue("rap");

        //Serverinitilization
        port(9022);
        staticFiles.location("/public");
        ObjectMapper objectMapper = new ObjectMapper();

        get("/genre/christmas", (req, res) -> {
            res.type("application/json");
            res.header("Access-Control-Allow-Origin", "*");

            Preload preLoader = preload.getPreloader();
            Track track = preLoader.getTrackFromQueue("christmas");
            preLoader.CreateQueueUpdater(preload, "christmas");
            return objectMapper.writeValueAsString(track);
        });
        get("/genre/rock", (req, res) -> {
            res.type("application/json");
            res.header("Access-Control-Allow-Origin", "*");
            Preload preLoader = preload.getPreloader();
            Track track = preLoader.getTrackFromQueue("rock");
            preLoader.CreateQueueUpdater(preload, "rock");
            return objectMapper.writeValueAsString(track);
        });
        get("/genre/pop", (req, res) -> {
            res.type("application/json");
            res.header("Access-Control-Allow-Origin", "*");
            Preload preLoader = preload.getPreloader();
            Track track = preLoader.getTrackFromQueue("pop");
            preLoader.CreateQueueUpdater(preload, "pop");
            return objectMapper.writeValueAsString(track);
        });
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