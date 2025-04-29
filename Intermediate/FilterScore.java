import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class FilterScore {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the student CSV file: ");
        String filePath = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line = br.readLine();
            System.out.println("Students who scored more than 80 marks:");
            while ((line = br.readLine()) != null)
            {
                String[] columns = line.split(",");
                int marks = Integer.parseInt(columns[3].trim());
                if (marks > 80)
                {
                    System.out.println("ID: " + columns[0] +
                            ", Name: " + columns[1] +
                            ", Age: " + columns[2] +
                            ", Marks: " + columns[3]);
                }
            }
        }
        catch (IOException | NumberFormatException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        scanner.close();
    }
}
