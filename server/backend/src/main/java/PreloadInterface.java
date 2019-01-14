/**
 * Interface that links the endpoints with the Preloader class
 * Author: Mikael Lindfors
 */
public interface PreloadInterface {

    /**
     * Method used to fetch the preLoader object
     * @return Preloader object
     */
    public Preload getPreloader();

    /**
     * Method that used to get a track object. Not used
     * @return track object
     */
    public Track getTrack();

    /**
     * Method that creates a thread that updates the queue for a specified genre.
     * @param preloader - current Preloader object
     * @param genre String genre
     */
    public void CreateQueueUpdater(Preload preloader, String genre) ;
}
