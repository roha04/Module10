import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordFrequencyCounter {

    public static Map<String, Integer> countWordFrequency(String fileName) {
        Map<String, Integer> frequencyMap = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort the frequency map by value in descending order
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Create a new LinkedHashMap to store the sorted entries
        Map<String, Integer> sortedFrequencyMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedFrequencyMap.put(entry.getKey(), entry.getValue());
        }

        return sortedFrequencyMap;
    }

    public static void main(String[] args) {
        String fileName = "C:\\Users\\Rostyslav\\IdeaProjects\\Module10\\src\\words.txt";
        Map<String, Integer> frequencyMap = countWordFrequency(fileName);

        // Print the word frequency in descending order
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}