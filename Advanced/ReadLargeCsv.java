import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class ReadLargeCsv {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the large CSV file: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter the output file path: ");
        String outputPath = scanner.nextLine();
        int chunkSize = 100;
        int totalRecordsProcessed = 0;
        int linesRead = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             FileWriter writer = new FileWriter(outputPath))
        {
            String line;
            if ((line = reader.readLine()) != null)
            {
                writer.write(line + "\n");
                linesRead++;
            }
            while ((line = reader.readLine()) != null)
            {
                writer.write(line + "\n");
                linesRead++;
                if (linesRead % chunkSize == 0)
                {
                    totalRecordsProcessed += chunkSize;
                    System.out.println("Processed " + chunkSize + " records.");
                }
            }
            if (linesRead % chunkSize != 0)
            {
                totalRecordsProcessed += linesRead % chunkSize;
                System.out.println("Processed remaining " + (linesRead % chunkSize) + " records.");
            }
            System.out.println("Total records processed: " + totalRecordsProcessed);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            scanner.close();
        }
    }
}
