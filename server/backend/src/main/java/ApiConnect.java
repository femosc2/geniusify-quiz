
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;
import org.apache.tomcat.util.codec.binary.Base64;
import com.fasterxml.jackson.databind.*;

public class ApiConnect {
    private Gson gson;
    private SendObject sendObject;
    private Token token;
    private int counter = 0;

    ApiConnect() {
        gson = new Gson();
    }

    public SendObject generateSendObject(String genre) {
        return sendObject;
    }
    public Token getToken() {
        return token;
    }
    public void setToken(Token token) {
        this.token = token;
    }

    public String cropSongName(String songName) {
        String result = "";
        if (songName.contains("(")) {
            System.out.println("Modiferar låttitel från: " + songName);
            result = songName.substring(0,songName.indexOf('('));
            System.out.println("till: " + result);
        }else if(songName.contains("-")) {
            System.out.println("Modifierar låttitel från: " + songName);
            result = songName.substring(0,songName.indexOf('-'));
            System.out.println("till:" + result);
        }else {
            return songName;
        }
        return result;
    }

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
//            System.out.println(response.body().string());
            LyricsResult res = gson.fromJson(response.body().string(), LyricsResult.class);
            System.out.println(counter);
            return res.getResult().getTrack().getText();

        } catch (Exception e) { return "Låttext hittas ej för: " + artist + " - " + song;

        }

    }

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
//            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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
            JsonNode node = objectMapper.readValue(resultBody, JsonNode.class);

//            for (int i = 0; i < 10; i++) {
                JsonNode arrayTest = new ObjectMapper().readTree(resultBody).get("items");
                for (JsonNode items: arrayTest) {
                    JsonNode track = items.get("track"); //0
                        JsonNode trackNameUnChopped = track.get("name"); //1
                        JsonNode album = track.get("album"); //1
                            JsonNode imageArray = album.get("images"); //2
                                JsonNode imageURLarray = imageArray.get(1); //BILDURL //3
                                    JsonNode imageURL = imageURLarray.get("url"); // 4
                        JsonNode artistArray = track.get("artists"); //1
                            JsonNode artist = artistArray.get(0); //1
                                JsonNode artistName = artist.get("name"); //2


                    String trackName = trackNameUnChopped.asText();
                    if ( trackName.indexOf("-") > -1 ) {
                        trackName = trackName.substring(0, trackName.indexOf("-"));
                    }
                    result.addTrack( new Track(trackName, artistName.asText(), imageURL.asText()));
                //          public Track (String name, String artist, String imageUrl) {
                }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

//       const getChristmasPlaylist = () => {
//        axios.get("https://api.spotify.com/v1/playlists/5OP7itTh52BMfZS1DJrdlv/tracks", {
//                headers: {
//            'Authorization': "Bearer " + token
//        }
//        })
//        .then((response) => {
//            const resultArray = [];
//        for (let key in response) {
//            resultArray.push(response[key]);
//        }
//        let playlist = resultArray;
//        let randomNumber = Math.floor(Math.random() * 101)
//        christmasSong = playlist[5].items[randomNumber].track.name
//        christmasArtist = playlist[5].items[randomNumber].track.artists[0].name
//        christmasImage = playlist[5].items[randomNumber].track.album.images[1].url
//        console.log("Christmas song updated!")
//        return playlist
//        // console.log(playlist)
//        })
//        .catch((error) => {
//                console.log("Access token not yet updated, please wait 5 seconds.");
//        })
//    }

//    public void getToken() {
//        try {
//            String url = "https://accounts.spotify.com/api/token";
//            String client_id = "edb6f939e332455483f8e58066d110aa";
//            String client_secret = "cadb6cd3c34f4863a85ba3b246857d2a";
//            String identification = client_id + ":" + client_secret;
//            String idEncoded = new String(Base64.encodeBase64(identification.getBytes()));
//            HttpClient client = HttpClientBuilder.create().build();
//            HttpPost post = new HttpPost(url);
//            post.setHeader("Content-type", "application/x-www-form-urlencoded");
//            post.setHeader("Authorization", "Basic " + idEncoded);
//            List<NameValuePair> urlParameters = new ArrayList();
//            urlParameters.add(new BasicNameValuePair("grant_type", "client_credentials"));
//
//            post.setEntity(new UrlEncodedFormEntity(urlParameters));
//
//            HttpResponse response = client.execute(post);
//
//            System.out.println("Response code : " + response.getStatusLine().getStatusCode());
//
//            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            StringBuffer result = new StringBuffer();
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//            System.out.println(result.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//    }

//    public String PostTextToTranslation(String translationText) {
//        OkHttpClient client = new OkHttpClient();
//        String subscriptionKey = "4338ddc4a9814dd58342519623a06b5d";
//        String url = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&to=de";
//        MediaType mediaType = MediaType.parse("application/json");
////        RequestBody body = RequestBody.create(mediaType, "[{\n\t\"Text\": \" + translationText + Welcome to Microsoft Translator. Guess how many languages I speak!\"\n}]");
//        RequestBody body = RequestBody.create(mediaType, "[{\n\t\"Text\": \" " + translationText + "\"\n}]");
//        Request request = new Request.Builder()
//                .url(url).post(body)
//                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
//                .addHeader("Content-type", "application/json").build();
//        try {
//            Response response = client.newCall(request).execute();
//            return response.body().string();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "nått gick fel :/";
//    }
