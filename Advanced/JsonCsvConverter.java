import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
public class JsonCsvConverter {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the full path of the input JSON file: ");
        String inputJsonPath = scanner.nextLine();
        System.out.print("Enter the full path for the output CSV file: ");
        String outputCsvPath = scanner.nextLine();
        scanner.close();
        try
        {
            convertJsonToCsv(inputJsonPath, outputCsvPath);
            System.out.println("✅ CSV file created successfully at: " + outputCsvPath);
        }
        catch (Exception e)
        {
            System.err.println("Error during conversion: ");
            e.printStackTrace();
        }
    }
    public static void convertJsonToCsv(String jsonFilePath, String csvFilePath) throws IOException
    {
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode jsonTree = jsonMapper.readTree(new File(jsonFilePath));
        if (!jsonTree.isArray() || jsonTree.isEmpty())
            throw new IllegalArgumentException("Input JSON must be a non-empty array of objects.");
        Iterator<String> fieldNames = jsonTree.elements().next().fieldNames();
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        fieldNames.forEachRemaining(csvSchemaBuilder::addColumn);
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File(csvFilePath), jsonTree);
    }
}
