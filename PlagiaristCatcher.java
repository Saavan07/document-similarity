import java.io.*;
import java.util.*;

/** Command-line interface for the n-gram overlap course project. */
public class PlagiaristCatcher {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String directory;
        int phraseLength;
        int minHits;
        if (args.length == 3) {
            directory = args[0];
            phraseLength = Integer.parseInt(args[1]);
            minHits = Integer.parseInt(args[2]);
        } else if (args.length == 0) {
            File[] folders = new File(".").listFiles(folder -> {
                if (!folder.isDirectory() || folder.isHidden()) return false;
                String[] documents = folder.list((dir, name) -> name.endsWith(".txt"));
                return documents != null && documents.length > 0;
            });
            if (folders == null || folders.length == 0) {
                System.out.println("No document folders found. Usage: java PlagiaristCatcher <folder> <phrase-length> <minimum-hits>");
                return;
            }
            Arrays.sort(folders, Comparator.comparing(File::getName));
            for (int i = 0; i < folders.length; i++)
                System.out.println(i + " -> " + folders[i].getName());
            System.out.print("Choose a document folder: ");
            int selected = input.nextInt();
            while (selected < 0 || selected >= folders.length) {
                System.out.print("Choose a listed folder number: ");
                selected = input.nextInt();
            }
            directory = folders[selected].getPath();
            System.out.print("Phrase length (>= 1): ");
            phraseLength = input.nextInt();
            System.out.print("Minimum hits (>= 1): ");
            minHits = input.nextInt();
        } else {
            throw new IllegalArgumentException("Usage: java PlagiaristCatcher <folder> <phrase-length> <minimum-hits>");
        }
        if (phraseLength < 1 || minHits < 1)
            throw new IllegalArgumentException("Phrase length and minimum hits must be positive.");
        if (!new File(directory).isDirectory())
            throw new IllegalArgumentException("Document folder does not exist: " + directory);
        List<String> results = new HitList(directory, phraseLength, minHits).createList();
        System.out.println("Completed hit list (minimum of " + minHits + " hits of " + phraseLength + " word contiguous phrases):");
        for (String result : results) System.out.println(result);
    }
}
