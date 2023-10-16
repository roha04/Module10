import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {

    public static void findValidPhoneNumbers(String fileName) {
        URL url = PhoneNumberValidator.class.getResource(fileName);
        if (url == null) {
            System.out.println("File not found: " + fileName);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(url.toURI()).toFile()))) {
            String line;
            Pattern pattern = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}");

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    System.out.println(line);
                }
            }
        } catch (IOException | java.net.URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "/file.txt"; // Adjust the path if necessary
        findValidPhoneNumbers(fileName);
    }
}