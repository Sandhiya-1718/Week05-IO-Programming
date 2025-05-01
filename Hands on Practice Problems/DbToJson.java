import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.io.FileWriter;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
public class DbToJson {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to save the JSON report: ");
        String outputPath = scanner.nextLine();
        scanner.close();
        String url = "jdbc:mysql://localhost/javademodb";
        String user = "root";
        String password = "Purplesky@1234";
        String query = "SELECT * FROM employee";
        try(
                Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery()
        )
        {
            List<Map<String, Object>> dataList = new ArrayList<>();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next())
            {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++)
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                dataList.add(row);
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(outputPath), dataList);
            System.out.println("JSON report successfully saved at: " + outputPath);
        }
        catch (Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }
}
