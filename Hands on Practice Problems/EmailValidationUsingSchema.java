import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileInputStream;
import java.util.Scanner;
public class EmailValidationUsingSchema {
    public static void main(String[] args)
    {
        try
        {
            Scanner scanner=new Scanner(System.in);
            System.out.println("Enter the JSON file to validate:");
            FileInputStream schemaStream = new FileInputStream(scanner.nextLine());
            JSONObject schemaJson = new JSONObject(new JSONTokener(schemaStream));
            Schema schema = SchemaLoader.load(schemaJson);
            JSONObject userData = new JSONObject();
            userData.put("name", "Alice");
            userData.put("email", "alice@example.com");
            schema.validate(userData);
            System.out.println("JSON is valid according to the schema.");
        }
        catch (Exception e)
        {
            System.err.println("Validation error: " + e.getMessage());
        }
    }
}
