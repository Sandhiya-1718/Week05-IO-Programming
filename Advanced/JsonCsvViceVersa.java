import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.MappingIterator;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class JsonCsvViceVersa {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path of input JSON file: ");
        String jsonInputPath = scanner.nextLine();
        System.out.print("Enter path to save output CSV file: ");
        String csvOutputPath = scanner.nextLine();
        System.out.print("Enter path to save converted back JSON from CSV: ");
        String jsonOutputFromCsvPath = scanner.nextLine();
        scanner.close();
        try
        {
            convertJsonToCsv(jsonInputPath, csvOutputPath);
            convertCsvToJson(csvOutputPath, jsonOutputFromCsvPath);
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void convertJsonToCsv(String jsonFilePath, String csvFilePath) throws IOException
    {
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode jsonTree = jsonMapper.readTree(new File(jsonFilePath));
        if (!jsonTree.isArray() || jsonTree.isEmpty())
            throw new IllegalArgumentException("Input JSON must be a non-empty array.");
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        jsonTree.get(0).fieldNames().forEachRemaining(schemaBuilder::addColumn);
        CsvSchema csvSchema = schemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File(csvFilePath), jsonTree);
        System.out.println("✅ JSON to CSV conversion successful: " + csvFilePath);
    }
    public static void convertCsvToJson(String csvFilePath, String jsonFilePath) throws IOException
    {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        ObjectMapper jsonMapper = new ObjectMapper();
        MappingIterator<JsonNode> it = csvMapper.readerFor(JsonNode.class)
                .with(csvSchema)
                .readValues(new File(csvFilePath));
        ArrayNode jsonArray = jsonMapper.createArrayNode();
        while (it.hasNext())
            jsonArray.add(it.next());
        jsonMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(jsonFilePath), jsonArray);
        System.out.println("✅ CSV to JSON conversion successful: " + jsonFilePath);
    }
}
