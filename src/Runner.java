import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Runner {
    private static final String filename = "input.txt";

    public static void solve(String startingWord, String endingWord) {
        WordLadder wordLadder = new WordLadder(startingWord, endingWord);
        Stack<String> solution = wordLadder.solve();
        if (solution != null)
            System.out.printf("Found a ladder! >>> %s%n", solution);
        else
            System.out.printf("No ladder between %s and %s%n", startingWord, endingWord);
        }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().split(" ");
                solve(words[0], words[1]);
        }}
        catch (FileNotFoundException exception) {
            System.out.println("File not found: " + filename);
            System.exit(1);
}}}