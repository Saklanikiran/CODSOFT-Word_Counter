// Code by : Kiran
// Task 2 : Word counter

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCount {

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "a", "an", "the", "in", "on", "at", "of", "to", "for", "with", "and", "or", "is", "are", "am", "was", "were"));
            

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter '1' to provide text input or '2' to provide a file path:");
        int choice = scanner.nextInt();

        String inputText;
        switch (choice) {
            case 1:
                scanner.nextLine(); 
                System.out.println("Enter the text:");
                inputText = scanner.nextLine();
                break;
            case 2:
                scanner.nextLine(); 
                System.out.println("Enter the file path:");
                String filePath = scanner.nextLine();
                inputText = readFile(filePath);
                if (inputText == null) {
                    System.out.println("Error reading file.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        int wordCount = countWords(inputText);
        System.out.println("Total word count: " + wordCount);

        Map<String, Integer> wordFrequencyMap = getWordFrequency(inputText);
        System.out.println("Number of unique words: " + wordFrequencyMap.size());

       
    }

    private static String readFile(String filePath) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int countWords(String inputText) {
        String[] words = inputText.split("\\s+|\\p{Punct}+");
        int count = 0;
        for (String word : words) {
            if (!word.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static Map<String, Integer> getWordFrequency(String inputText) {
        String[] words = inputText.split("\\s+|\\p{Punct}+");
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty() && !STOP_WORDS.contains(word.toLowerCase())) {
                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
            }
        }
        return wordFrequencyMap;
    }

    private static void displayWordFrequency(Map<String, Integer> wordFrequencyMap) {
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
