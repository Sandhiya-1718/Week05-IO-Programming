import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
public class ModifyCsv {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the employee CSV file: ");
        String inputFilePath = scanner.nextLine();
        System.out.print("Enter the path to save the modified CSV file: ");
        String outputFilePath = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath)))
        {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null)
            {
                if (isHeader)
                {
                    bw.write(line + "\n");
                    isHeader = false;
                    continue;
                }
                String[] columns = line.split(",");
                if (columns.length < 4)
                {
                    System.out.println("Skipping invalid row: " + line);
                    continue;
                }
                String department = columns[2].trim();
                double salary = Double.parseDouble(columns[3].trim());
                if ("IT".equalsIgnoreCase(department))
                {
                    salary += salary * 0.10;
                    columns[3] = String.format("%.2f", salary);  // Update salary with 10% increase
                }
                bw.write(String.join(",", columns) + "\n");
            }
            System.out.println("CSV file updated successfully!");
        }
        catch (IOException e)
        {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
        finally
        {
            scanner.close();
        }
    }
}
