import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;
public class FilterJsonRecords {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter JSON file path:");
        String filePath = scanner.nextLine();
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            List<JsonNode> filteredRecords = new ArrayList<>();
            Iterator<JsonNode> elements = rootNode.elements();
            while (elements.hasNext())
            {
                JsonNode record = elements.next();
                if (record.has("age") && record.get("age").asInt() > 25)
                    filteredRecords.add(record);
            }
            System.out.println("Filtered Records (Age > 25):");
            for (JsonNode filteredRecord : filteredRecords)
                System.out.println(filteredRecord.toPrettyString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
