import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
public class ListToJsonArray {
    public static void main(String[] args)
    {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John Doe", 20, "john.doe@example.com"));
        students.add(new Student("Jane Smith", 22, "jane.smith@example.com"));
        students.add(new Student("Alice Brown", 23, "alice.brown@example.com"));
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonArray = objectMapper.writeValueAsString(students);
            System.out.println("JSON Array: " + jsonArray);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
class Student
{
    private String name;
    private int age;
    private String email;
    public Student(String name, int age, String email)
    {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
}
