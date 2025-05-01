import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class ExtractJsonFields {
    public static void main(String[] args)
    {
        try
        {
            Scanner scanner=new Scanner(System.in);
            System.out.print("Enter the path of the input file:");
            String input=scanner.nextLine();
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(input);
            JsonNode rootNode = objectMapper.readTree(file);
            for (JsonNode userNode : rootNode)
            {
                String name = userNode.path("name").asText();
                String email = userNode.path("email").asText();
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("------------");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
