import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Scanner;
public class FilterUsersByAge {
    public static void main(String[] args)
    {
        try
        {
            Scanner scanner=new Scanner(System.in);
            System.out.print("Enter the json file path:");
            String input=scanner.nextLine();
            ObjectMapper mapper = new ObjectMapper();
            File inputFile = new File(input);
            JsonNode rootNode = mapper.readTree(inputFile);

            if (!rootNode.isArray())
            {
                System.err.println("Expected a JSON array.");
                return;
            }
            System.out.println("✅ Users with age > 25:");
            for (JsonNode user : rootNode)
            {
                if (user.has("age") && user.get("age").asInt() > 25)
                    System.out.println(user.toPrettyString());
            }
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
