import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
public class MergeCsvFiles {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path for students1.csv: ");
        String students1Path = scanner.nextLine();
        System.out.print("Enter the path for students2.csv: ");
        String students2Path = scanner.nextLine();
        System.out.print("Enter the output file path for merged data (e.g., merged_students.csv): ");
        String outputPath = scanner.nextLine();
        List<String[]> students1 = readCSV(students1Path);
        List<String[]> students2 = readCSV(students2Path);
        List<String[]> mergedData = mergeCSVData(students1, students2);
        writeCSV(outputPath, mergedData);
        System.out.println("Merged CSV data saved to: " + outputPath);
    }
    public static List<String[]> readCSV(String filePath)
    {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");
                data.add(row);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return data;
    }
    public static List<String[]> mergeCSVData(List<String[]> students1, List<String[]> students2)
    {
        Map<String, String[]> students1Map = new HashMap<>();
        List<String[]> mergedData = new ArrayList<>();
        for (String[] student : students1)
            students1Map.put(student[0], student);
        for (String[] student2 : students2)
        {
            String[] student1 = students1Map.get(student2[0]);
            if (student1 != null)
            {
                String[] mergedStudent = new String[student1.length + student2.length - 1];
                System.arraycopy(student1, 0, mergedStudent, 0, student1.length);
                System.arraycopy(student2, 1, mergedStudent, student1.length, student2.length - 1);
                mergedData.add(mergedStudent);
            }
        }
        return mergedData;
    }
    public static void writeCSV(String filePath, List<String[]> data)
    {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath)))
        {
            for (String[] row : data)
            {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
