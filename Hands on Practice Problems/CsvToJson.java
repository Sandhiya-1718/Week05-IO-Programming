import com.opencsv.CSVReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class CsvToJson {
    public static void main(String[] args)
    {
        try (Scanner scanner = new Scanner(System.in))
        {
            System.out.print("Enter path for the input CSV file: ");
            String inputCsvFile = scanner.nextLine();
            System.out.print("Enter path to save the output JSON file: ");
            String outputJsonFile = scanner.nextLine();
            CSVReader reader = new CSVReader(new FileReader(inputCsvFile));
            List<String[]> rows = reader.readAll();
            String[] header = rows.get(0);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, String>> jsonData = new java.util.ArrayList<>();
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                Map<String, String> map = new java.util.HashMap<>();
                for (int j = 0; j < header.length; j++)
                    map.put(header[j], row[j]);
                jsonData.add(map);
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(outputJsonFile), jsonData);
            System.out.println("CSV successfully converted to JSON. Check the output at: " + outputJsonFile);
            reader.close();
        } catch (Exception e) {
            System.out.println("Error occurred:");
            e.printStackTrace();
        }
    }
}
