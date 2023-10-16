import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

 class Main {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();

        // Read data from file.txt and convert it into User objects
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Rostyslav\\IdeaProjects\\Module10\\src\\file\\file1.txt"));) {
            String line;
            boolean firstLine = true;
            String[] headers = null;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    headers = line.split(" ");
                    firstLine = false;
                } else {
                    String[] data = line.split(" ");
                    if (data.length == headers.length) {
                        String name = data[0];
                        int age = Integer.parseInt(data[1]);
                        userList.add(new User(name, age));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the list of User objects to user.json
        try (Writer writer = new FileWriter("user.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(userList, new TypeToken<List<User>>() {}.getType(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
