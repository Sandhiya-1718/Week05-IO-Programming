import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileWriter;
import java.util.Scanner;
public class ReadDatabase {
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the path of the file to store the CSV file:");
        String csvFilePath = scanner.nextLine();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javademodb", "root", "Purplesky@1234");
            String sql = "SELECT id, name, dept, salary FROM employee";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            FileWriter csvWriter = new FileWriter(csvFilePath);
            csvWriter.append("Employee ID,Name,Department,Salary\n");
            while (result.next())
            {
                int id = result.getInt("id");
                String name = result.getString("name");
                String department = result.getString("dept");
                int salary = result.getInt("salary");
                csvWriter.append(id + "," + name + "," + department + "," + salary + "\n");
            }
            csvWriter.flush();
            csvWriter.close();
            result.close();
            statement.close();
            connection.close();
            System.out.println("CSV file created successfully: " + csvFilePath);
        }
        catch (Exception e)
        {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }
    }
}
