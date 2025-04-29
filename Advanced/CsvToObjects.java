import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Student {
    private final int id;
    private final String name;
    private final int age;
    private final int marks;
    public Student(int id, String name, int age, int marks)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }
    @Override
    public String toString()
    {
        return "Student [ID=" + id + ", Name=" + name + ", Age=" + age + ", Marks=" + marks + "]";
    }
}
public class CsvToObjects {
    public static void main(String[] args)
    {
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the full path to the students.csv file: ");
        String filePath = scanner.nextLine();        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line = br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(",");
                if (data.length == 4)
                {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    int marks = Integer.parseInt(data[3]);
                    Student student = new Student(id, name, age, marks);
                    students.add(student);
                }
            }
            for (Student student : students)
                System.out.println(student);
        }
        catch (IOException e)
        {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Invalid number format in CSV: " + e.getMessage());
        }
        scanner.close();
    }
}
