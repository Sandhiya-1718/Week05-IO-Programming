import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class ValidateJsonStructure {
    public static void main(String[] args)
    {
        System.out.print("Enter the path of the input JSON file: ");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        scanner.close();
        boolean isValid = validateJsonStructure(filePath);
        if (isValid)
            System.out.println("JSON structure is valid!");
         else
            System.out.println("Invalid JSON structure.");
    }
    public static boolean validateJsonStructure(String filePath)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));
            if (jsonNode.has("name") && jsonNode.has("age") && jsonNode.has("email"))
                return true;
            else
            {
                System.out.println("Missing required fields.");
                return false;
            }
        }
        catch (IOException e)
        {
            System.out.println("Invalid JSON format or error reading the file.");
            return false;
        }
    }
}
