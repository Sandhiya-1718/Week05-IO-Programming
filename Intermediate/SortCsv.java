import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
public class SortCsv {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the employee CSV file: ");
        String filePath = scanner.nextLine();
        scanner.close();
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String header = br.readLine();
            if (header == null)
            {
                System.out.println("Empty file.");
                return;
            }
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] columns = line.split(",");
                if (columns.length >= 4)
                    records.add(columns);
            }
            records.sort(new Comparator<String[]>() {
                public int compare(String[] a, String[] b)
                {
                    double salaryA = Double.parseDouble(a[3].trim());
                    double salaryB = Double.parseDouble(b[3].trim());
                    return Double.compare(salaryB, salaryA);
                }
            });
            System.out.println("\nTop 5 highest-paid employees:");
            System.out.println(header);
            for (int i = 0; i < Math.min(5, records.size()); i++)
            {
                String[] emp = records.get(i);
                System.out.println(String.join(",", emp));
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Invalid salary value in file.");
        }
        scanner.close();
    }
}
