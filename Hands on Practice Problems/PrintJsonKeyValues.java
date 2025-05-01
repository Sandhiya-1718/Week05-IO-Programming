import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
public class PrintJsonKeyValues {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the JSON file: ");
        String filePath = scanner.nextLine();
        scanner.close();
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(filePath));
            printJson(rootNode, "");
        }
        catch (IOException e)
        {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
    public static void printJson(JsonNode node, String parentKey)
    {
        if (node.isObject())
        {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext())
            {
                Map.Entry<String, JsonNode> entry = fields.next();
                printJson(entry.getValue(), parentKey.isEmpty() ? entry.getKey() : parentKey + "." + entry.getKey());
            }
        }
        else if (node.isArray())
        {
            int index = 0;
            for (JsonNode arrayElement : node)
            {
                printJson(arrayElement, parentKey + "[" + index + "]");
                index++;
            }
        }
        else
            System.out.println(parentKey + " : " + node.asText());
    }
}
