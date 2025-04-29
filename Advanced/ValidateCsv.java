import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class ValidateCsv {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the CSV file path: ");
        String filePath = scanner.nextLine();
        scanner.close();
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$";
        String phoneRegex = "^\\d{10}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Pattern phonePattern = Pattern.compile(phoneRegex);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String header = br.readLine();
            if (header == null)
            {
                System.out.println("Empty file.");
                return;
            }
            String line;
            int row = 2;
            while ((line = br.readLine()) != null)
            {
                String[] columns = line.split(",");
                if (columns.length < 4)
                {
                    System.out.println("Row " + row + ": Incomplete data.");
                    row++;
                    continue;
                }
                String email = columns[2].trim();
                String phone = columns[3].trim();
                Matcher emailMatcher = emailPattern.matcher(email);
                Matcher phoneMatcher = phonePattern.matcher(phone);
                if (!emailMatcher.matches())
                    System.out.println("Row " + row + ": Invalid email -> " + email);
                if (!phoneMatcher.matches())
                    System.out.println("Row " + row + ": Invalid phone number -> " + phone);
                row++;
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
