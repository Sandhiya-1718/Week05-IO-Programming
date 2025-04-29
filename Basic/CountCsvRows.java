import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class CountCsvRows {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the CSV file: ");
        String filePath = scanner.nextLine();
        int recordCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null)
            {
                if (isFirstLine)
                {
                    isFirstLine = false;
                    continue;
                }
                recordCount++;
            }

            System.out.println("Number of records (excluding header): " + recordCount);
        } catch (IOException e) {
            System.err.println("Error reading the file:");
            e.printStackTrace();
        }
        scanner.close();
    }
}
