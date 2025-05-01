import org.json.JSONObject;
import org.json.XML;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
public class JsonToXml{
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter path for the input JSON file: ");
            String inputFilePath = scanner.nextLine();
            System.out.print("Enter path to save the output XML file: ");
            String outputFilePath = scanner.nextLine();
            String content = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            JSONObject jsonObject = new JSONObject(content);
            String xml = XML.toString(jsonObject);
            Files.write(Paths.get(outputFilePath), xml.getBytes());
            System.out.println("JSON successfully converted to XML. Check the output at: " + outputFilePath);
        } catch (Exception e) {
            System.out.println("Error occurred:");
            e.printStackTrace();
        }
    }
}
