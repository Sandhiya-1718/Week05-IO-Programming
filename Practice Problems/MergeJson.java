import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class MergeJson {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path for the first JSON file: ");
        String filePath1 = scanner.nextLine();
        System.out.print("Enter the path for the second JSON file: ");
        String filePath2 = scanner.nextLine();
        scanner.close();
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json1 = objectMapper.readTree(new File(filePath1));
            JsonNode json2 = objectMapper.readTree(new File(filePath2));
            JsonNode mergedJson = mergeJson(json1, json2);
            System.out.println("Merged JSON: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedJson));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static JsonNode mergeJson(JsonNode json1, JsonNode json2)
    {
        ObjectNode mergedNode = (ObjectNode) json1;
        json2.fieldNames().forEachRemaining(fieldName -> {
            JsonNode value = json2.get(fieldName);
            mergedNode.set(fieldName, value);
        });
        return mergedNode;
    }
}
