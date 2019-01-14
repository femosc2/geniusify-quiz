import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that handles lyrics and removes random words and store them in a track object
 * Author: Mikael Lindfors
 */

public class LyricsRemove {
    private final int numberOfWordsToRemove = 5;
    /**
     * Method that removes a random word from each line in the lyrics and stores the removed
     * words in a track-object
     * @param track Track to modify lyrics on.
     * @return track with modified lyrics on.
     */
    public Track lyricsRemover(Track track) {
        ArrayList<String> wordsRemovedList = new ArrayList();
        StringBuilder lyrics = new StringBuilder();
        String line = "";
        String result = "";
        Random rand = new Random();
        Scanner lineScanner = new Scanner(track.getLyrics());
        int counter = 0;
        while (lineScanner.hasNextLine() && counter < numberOfWordsToRemove) {
            line = lineScanner.nextLine();
            if (!line.equals("")) {
                counter++;
                String[] words = line.split(" ");
                int wordToBeRemoved;
                do {
                    wordToBeRemoved = rand.nextInt(words.length);
                } while (words[wordToBeRemoved].contains("\n") || words[wordToBeRemoved].contains("\r"));
                wordsRemovedList.add(words[wordToBeRemoved]);
                words[wordToBeRemoved] = "_____";
                for (int i = 0; i < words.length; i++) {
                    lyrics.append(words[i] + " ");
                }
                lyrics.append("\n");
            }
        }
        int nbrOfWordsRemoved = wordsRemovedList.size();
        String[] wordsRem = new String[nbrOfWordsRemoved];
        for (int i = 0; i < nbrOfWordsRemoved; i++) {
            wordsRem[i] = wordsRemovedList.remove(0);
        }
        track.setWords(wordsRem);
        track.setLyrics(lyrics.toString());
        System.out.println("text klar");
        return track;
    }
}

