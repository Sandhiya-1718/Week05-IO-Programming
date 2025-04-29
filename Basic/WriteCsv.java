import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class WriteCsv {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the CSV file to store: ");
        String filePath = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            writer.write("ID,Name,Department,Salary\n");
            writer.write("101,John Doe,Engineering,60000\n");
            writer.write("102,Jane Smith,HR,55000\n");
            writer.write("103,Michael Brown,Marketing,52000\n");
            writer.write("104,Alice Johnson,Finance,61000\n");
            writer.write("105,Bob Williams,Sales,57000\n");
            System.out.println("Employee data written successfully to " + filePath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        scanner.close();
    }
}
