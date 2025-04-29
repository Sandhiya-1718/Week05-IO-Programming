import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class ReadCsv{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the CSV file: ");
        String filePath = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] columns = line.split(",");
                if (columns[0].equals("ID"))
                    continue;
                System.out.println("Student ID: " + columns[0]);
                System.out.println("Name: " + columns[1]);
                System.out.println("Age: " + columns[2]);
                System.out.println("Marks: " + columns[3]);
                System.out.println("-------------------------");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        scanner.close();
    }
}
