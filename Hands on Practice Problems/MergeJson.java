import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;
public class MergeJson{
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in))
        {
            System.out.print("Enter path for first JSON file: ");
            String filePath1 = scanner.nextLine();
            System.out.print("Enter path for second JSON file: ");
            String filePath2 = scanner.nextLine();
            System.out.print("Enter path to save the merged JSON file: ");
            String outputFilePath = scanner.nextLine();
            String content1 = new String(Files.readAllBytes(Paths.get(filePath1)));
            String content2 = new String(Files.readAllBytes(Paths.get(filePath2)));
            JSONObject json1 = new JSONObject(content1);
            JSONObject json2 = new JSONObject(content2);
            for (String key : json2.keySet())
                json1.put(key, json2.get(key));
            try (FileWriter file = new FileWriter(outputFilePath))
            {
                file.write(json1.toString(4));
            }
            System.out.println("Merged JSON saved successfully to: " + outputFilePath);
        }
        catch (Exception e)
        {
            System.out.println("Error occurred:");
            e.printStackTrace();
        }
    }
}
