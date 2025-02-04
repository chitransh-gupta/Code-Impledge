import java.io.*;
import java.util.*;

class FindNode {
    Map<Character, FindNode> child;
    boolean isEndOfWord;

    FindNode() {
        child = new HashMap<>();
        isEndOfWord = false;
    }
}

class Find {
    FindNode root;

    Find() {
        root = new FindNode();
    }

    void insert(String word) {
        FindNode node = root;
        for (char c : word.toCharArray()) {
            node.child.putIfAbsent(c, new FindNode());
            node = node.child.get(c);
        }
        node.isEndOfWord = true;
    }

    boolean search(String word) {
        FindNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.child.containsKey(c)) {
                return false;
            }
            node = node.child.get(c);
        }
        return node.isEndOfWord;
    }
}

public class CompoundedWordsSolution {
    private static boolean isCompoundedWord(String word, Find find, Map<String, Boolean> newWord) {
        if (newWord.containsKey(word)) {
            return newWord.get(word);
        }
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            if (find.search(prefix)) {
                if (find.search(suffix) || isCompoundedWord(suffix, find, newWord)) {
                    newWord.put(word, true);
                    return true;
                }
            }
        }
        newWord.put(word, false);
        return false;
    }

    private static void findCompoundedWords(String filePath) {
        long startTime = System.currentTimeMillis();
        Find find = new Find();
        List<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String word = line.trim();
                words.add(word);
                find.insert(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> compoundedWords = new ArrayList<>();
        Map<String, Boolean> newWord = new HashMap<>();

        // Sort words by length to ensure smaller words are processed first
        words.sort(Comparator.comparingInt(String::length));

        for (String word : words) {
            if (isCompoundedWord(word, find, newWord)) {
                compoundedWords.add(word);
            }
        }

        // Sort compounded words by length in descending order
        compoundedWords.sort((a, b) -> Integer.compare(b.length(), a.length()));

        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        if (compoundedWords.size() >= 1) {
            System.out.println("Longest Compound Word: " + compoundedWords.get(0));
        }
        if (compoundedWords.size() >= 2) {
            System.out.println("Second Longest Compound Word: " + compoundedWords.get(1));
        }
        System.out.println("Time taken to process file " + filePath + ": " + timeTaken + " milliseconds");
    }

    public static void main(String[] args) {
        String inputFile1 = "Input_01.txt";
        String inputFile2 = "Input_02.txt";

        System.out.println("Processing " + inputFile1 + ":");
        findCompoundedWords(inputFile1);

        System.out.println("\nProcessing " + inputFile2 + ":");
        findCompoundedWords(inputFile2);
    }
}