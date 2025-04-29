import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class SearchEmployee {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the employee CSV file: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter the employee name to search for: ");
        String searchName = scanner.nextLine().trim();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            boolean isHeader = true;
            boolean found = false;
            while ((line = br.readLine()) != null)
            {
                if (isHeader)
                {
                    isHeader = false;
                    continue;
                }
                String[] columns = line.split(",");
                if (columns.length < 4)
                {
                    System.out.println("Skipping invalid row: " + line);
                    continue;
                }
                String name = columns[1].trim();
                if (name.equalsIgnoreCase(searchName))
                {
                    System.out.println("Employee found!");
                    System.out.println("Name: " + name);
                    System.out.println("Department: " + columns[2].trim());
                    System.out.println("Salary: " + columns[3].trim());
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                System.out.println("Employee with name '" + searchName + "' not found.");
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
        finally
        {
            scanner.close();
        }
    }
}
