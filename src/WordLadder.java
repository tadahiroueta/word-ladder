import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class WordLadder {
    final String dictionaryFilename = "dictionary.txt";

    private Queue<Stack<String>> queue;
    private ArrayList<String> dictionary;
    private String startingWord, endingWord;
    private Stack<String> solution;

    public WordLadder(String startingWord, String endingWord) {
        this.startingWord = startingWord;
        this.endingWord = endingWord;
        queue = new LinkedList<Stack<String>>();
        dictionary = getDictionary(); // O(n)
    }

    private ArrayList<String> getDictionary() {
        ArrayList<String> dictionary = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File(dictionaryFilename));
            while (scanner.hasNextLine()) 
                dictionary.add(scanner.nextLine().toLowerCase());    
        }
        catch(FileNotFoundException exception) {
            System.out.println("File not found: " + dictionaryFilename);
            System.exit(1);
        }
        dictionary = filterByWordSize(dictionary);
        return dictionary;
    }

    private ArrayList<String> filterByWordSize(ArrayList<String> dictionary) {
        for (int i = dictionary.size() - 1; i >= 0; i--)
            if (dictionary.get(i).length() != startingWord.length())
                dictionary.remove(i);

        return dictionary;
    }

    private int dictionaryIndexOf(String word) {
        int low, high;
        low = 0;
        high = dictionary.size() - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            int comparison = dictionary.get(mid).compareTo(word);
            if (comparison == 0)
                return mid;

            if (comparison > 0)
                high = mid - 1;

            else
                low = mid + 1;
        }
        if (dictionary.get(low).equals(word))
            return low;

        return -1;
    }

    public Stack<String> solve() {
        if (solution != null)
            return solution;

        if (dictionaryIndexOf(startingWord) == -1 || dictionaryIndexOf(endingWord) == -1)
            return null;

        dictionary.remove(startingWord); // O(n)

        Stack<String> startingStack = new Stack<String>();
        startingStack.push(startingWord);
        queue.add(startingStack);
        
        while (!queue.isEmpty()) {
            Stack<String> currentStack = queue.remove();
            String currentWord = currentStack.peek();

            for (int letterIndex = 0; letterIndex < currentWord.length(); letterIndex++)
                for (char c = 'a'; c <= 'z'; c++) {
                    String potentialWord = currentWord.substring(0, letterIndex) + c + currentWord.substring(letterIndex + 1);
        
                    if (potentialWord.equals(endingWord)) {
                        currentStack.push(potentialWord);
                        solution = currentStack;
                        return solution;
                    }
                    int dictionaryIndex = dictionaryIndexOf(potentialWord);
        
                    if (dictionaryIndex != -1) {
                        Stack<String> newStack = (Stack<String>) currentStack.clone();
                        newStack.push(potentialWord);
                        queue.add(newStack);
                        dictionary.remove(dictionaryIndex);
        }}}
        System.out.println("No solution found.");
        return null;
}}
