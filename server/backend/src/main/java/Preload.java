import java.util.LinkedList;
import java.util.Random;

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

    //LADDA BARA SPELLISTOR HÄR
    public void loadSongs() {
        PrepareQueue pq = new PrepareQueue();
        pq.startPrepareQueue();
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Track getTrackFromQueue(String genre) {
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

    //FYLL KÖER MED SIZE < 1
    public void fillQueue(String genre) {
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

    @Override
    public Preload getPreloader() {
        return this;
    }

    @Override
    public Track getTrack() {
        return null;
    }

    @Override
    public void CreateQueueUpdater(Preload preloader, String genre) {
        QueueUpdater queueUpdater = new QueueUpdater(preloader, genre);
        queueUpdater.startUpdate();
    }

    private class QueueUpdater extends Thread {
        private Thread thread;
        private Preload preload;
        private String genre;

        public QueueUpdater(Preload preload, String genre) {
            this.preload = preload;
            this.genre = genre;
        }

        public void startUpdate() {
            thread = new Thread(this);
            thread.start();
        }

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
        public void run() {
            try {
                preload.fillQueue(genre);
                stopThread();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class PrepareQueue extends Thread {
        private Thread thread;

        public void startPrepareQueue() {
            thread = new Thread(this);
            thread.start();
        }

        public void stopThread() {
            try {
                if (thread != null) {
                    thread.stop();
                    thread = null;
                }
            } catch (Exception e) {
            }
        }

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
                fillQueue("christmas");
                Thread.sleep(1000);
                fillQueue("rock");
                Thread.sleep(1000);
                fillQueue("pop");
                Thread.sleep(1000);
                fillQueue("rap");
                System.out.println("Laddat klart texter");
                stopThread();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private class TokenUpdater extends Thread {
        private Thread thread;
        private Preload preload;

        public TokenUpdater(Preload preload) {
            this.preload = preload;
            startTokenUpdater();
        }

        public void startTokenUpdater() {
            thread = new Thread(this);
            thread.start();
        }

        public void stopThread() {
            try {
                if (thread != null) {
                    thread.stop();
                    thread = null;
                }
            } catch (Exception e) {
            }
        }

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