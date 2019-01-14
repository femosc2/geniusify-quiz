import java.util.LinkedList;
import java.util.Random;

/**
 * Class that handles preloading songs and threads for filling track-queues.
 * Author: Mikael Lindfors
 */
public class Preload implements PreloadInterface {

    private LinkedList<Track> christmasQueue;
    private LinkedList<Track> rockQueue;
    private LinkedList<Track> popQueue;
    private LinkedList<Track> rapQueue;
    private ApiConnect apiConnect;
    private Token token;
    private LyricsRemove lyricsRemover;
    private PlayList christmasPlayList;
    private PlayList rockPlayList;
    private PlayList popPlayList;
    private PlayList rapPlayList;

    /**
     * Constructor that initialize all the playList, Track queues, LyricsRemover, loading new Songs
     * and starting a thread that refreshes the Spotify token
     * @param token Spotifytoken object
     * @param apiConnect ApiConnect object
     */
    public Preload(Token token, ApiConnect apiConnect) {
        this.christmasQueue = new LinkedList();
        this.rockQueue = new LinkedList();
        this.popQueue = new LinkedList();
        this.rapQueue = new LinkedList();
        this.apiConnect = apiConnect;
        this.token = token;
        this.lyricsRemover = new LyricsRemove();
        this.loadSongs();
        TokenUpdater tokenUpdater = new TokenUpdater(this);
    }

    /**
     * Method that starts a new thread and starts filling our track queues at start
     */
    public void loadSongs() {
        PrepareQueue pq = new PrepareQueue();
        pq.startPrepareQueue();
    }

    /**
     * Method that sets the spotifyToken
     * @param token Spotify token object
     */
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     * Method that fetches a track from one of the track-queues.
     * @param genre String genre of the requested track
     * @return Track object with a new track.
     */
    public synchronized Track getTrackFromQueue(String genre) {
        switch (genre) {
            case ("christmas"):
                return christmasQueue.removeFirst();
            case ("rock"):
                return rockQueue.removeFirst();
            case ("pop"):
                return popQueue.removeFirst();
            case ("rap"):
                return rapQueue.removeFirst();

        }
        return null;
    }

    /**
     * Synchronized method that fills a specific queue with a new random Track
     * @param genre String request genre
     */
    public synchronized void  fillQueue(String genre) {
        Random rand = new Random();
        Track track = null;
        switch (genre) {
            case ("christmas"): {
                do {
                    track = christmasPlayList.getTrack(rand.nextInt(100));
                    modifyLyricsInTrack(track);
                } while (track.getLyrics() == null || track.getLyrics().length() < 100);
                christmasQueue.add(track);
                break;
            }
            case ("rock"): {
                do {
                track = rockPlayList.getTrack(rand.nextInt(100));
                modifyLyricsInTrack(track);
                } while (track.getLyrics() == null || track.getLyrics().length() < 100);
                rockQueue.add(track);
                break;
            }
            case ("pop"): {
                do {
                    track = popPlayList.getTrack(rand.nextInt(100));
                modifyLyricsInTrack(track);
                } while (track.getLyrics() == null || track.getLyrics().length() < 100);
                popQueue.add(track);
                break;
            }
            case ("rap"): {
                do {track = rapPlayList.getTrack(rand.nextInt(100));
                modifyLyricsInTrack(track);
                } while (track.getLyrics() == null || track.getLyrics().length() < 100);
                rapQueue.add(track);
                break;
            }
        }
    }

    /**
     * Method that modify Lyrics in a trackObject
     * @param track Incoming track with lyrics that needs to be modified
     * @return The modified track
     */
    public Track modifyLyricsInTrack(Track track) {
        String lyrics = apiConnect.fetchLyrics(track.getArtist(), track.getName());
        track.setLyrics(lyrics);
        if (!lyrics.contains("Låttext")) {
            track = lyricsRemover.lyricsRemover(track);
        } else {
            System.out.println("nullar låttext");
            track.setLyrics(null);
        }
        return track;
    }

    /**
     * Method that returns current Preloader object.
     * @return Preload object
     */
    @Override
    public Preload getPreloader() {
        return this;
    }

    /**
     * Method not used
     * @return null
     */
    @Override
    public Track getTrack() {
        return null;
    }

    /**
     * Method that creates a QueueUpdater object and starts a thread that will update the queue.
     * from a specific genre
     * @param preloader - current Preloader object
     * @param genre String genre
     */
    @Override
    public synchronized void CreateQueueUpdater(Preload preloader, String genre) {
        QueueUpdater queueUpdater = new QueueUpdater(preloader, genre);
        queueUpdater.startUpdate();
    }

    /**
     * Private class used for a thread that updates the queues.
     * Author: Mikael Lindfors
     */
    private class QueueUpdater extends Thread {
        private Thread thread;
        private Preload preload;
        private String genre;

        /**
         * Construcot that sets the Preload object and the String of the genre
         * @param preload Preload object
         * @param genre String genre
         */
        public QueueUpdater(Preload preload, String genre) {
            this.preload = preload;
            this.genre = genre;
        }

        /**
         * Method that starts the thread
         */
        public void startUpdate() {
            thread = new Thread(this);
            thread.start();
        }

        /**
         * Method that stops the thread
         * thread.stop is deprecated but still used.
         */
        public void stopThread() {
            try {
                if (thread != null) {
                    thread.stop();
                    thread = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Method that fills a queue with one track and then ends the thread.
         */
        public void run() {
            try {
                preload.fillQueue(genre);
                stopThread();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Private class ued for preloading tracks for our track-queues
     * Author: Mikael Lindfors
     */
    private class PrepareQueue extends Thread {
        private Thread thread;

        /**
         * Constructor that initialize the thread and starts it
         */
        public void startPrepareQueue() {
            thread = new Thread(this);
            thread.start();
        }

        /**
         * Method that stops the thread
         */
        public void stopThread() {
            try {
                if (thread != null) {
                    thread.stop();
                    thread = null;
                }
            } catch (Exception e) {
            }
        }

        /**
         * Method that fetch a playList from each genre from spotify.
         * Thread.sleep between each request to not overflow the api with requests
         * When done it will fill the track queues with tracks and then end the thread.
         */
        public void run() {
            try {
                christmasPlayList = apiConnect.getPlayList("christmas");
                Thread.sleep(1000);
                popPlayList = apiConnect.getPlayList("rock");
                Thread.sleep(1000);
                rockPlayList = apiConnect.getPlayList("pop");
                Thread.sleep(1000);
                rapPlayList = apiConnect.getPlayList("rap");
                Thread.sleep(1000);
                System.out.println("laddat klart spellistor");
                for (int i =0;i<4;i++) {
                    fillQueue("christmas");
                    Thread.sleep(1000);
                    fillQueue("rock");
                    Thread.sleep(1000);
                    fillQueue("pop");
                    Thread.sleep(1000);
                    fillQueue("rap");
                }
                System.out.println("Laddat klart texter");
                stopThread();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Private class that handles the thread that updates the spotify authorization token.
     */
    private class TokenUpdater extends Thread {
        private Thread thread;
        private Preload preload;

        /**
         * Construcotr that sets the Preload object and starts the thread.
         * @param preload Preload object
         */
        public TokenUpdater(Preload preload) {
            this.preload = preload;
            startTokenUpdater();
        }

        /**
         * Method that starts the thread.
         */
        public void startTokenUpdater() {
            thread = new Thread(this);
            thread.start();
        }

        /**
         * Method that ends the thread
         */
        public void stopThread() {
            try {
                if (thread != null) {
                    thread.stop();
                    thread = null;
                }
            } catch (Exception e) {
            }
        }

        /**
         * Method that refresh the spotify authorization token every 58 minutes
         */
        public void run() {
            try {
                while (true) {
                    thread.sleep(3500000);
                    preload.setToken(apiConnect.getTokenFromSpotify());
                }
            } catch (Exception e) {
            }
        }
    }
}