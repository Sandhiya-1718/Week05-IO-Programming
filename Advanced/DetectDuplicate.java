import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
public class DetectDuplicate {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CSV file path: ");
        String filePath = scanner.nextLine();
        scanner.close();
        HashSet<String> seenIds = new HashSet<>();
        System.out.println("\nDuplicate Records Based on ID:\n");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null)
            {
                if (firstLine)
                {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length > 0)
                {
                    String id = parts[0].trim();
                    if (seenIds.contains(id))
                        System.out.println(line);
                    else
                        seenIds.add(id);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading file:");
            e.printStackTrace();
        }
    }
}
