import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
class Employee {
    private String name;
    private int age;
    private String department;
    public Employee(String name, int age, String department)
    {
        this.name = name;
        this.age = age;
        this.department = department;
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
    public String getDepartment()
    {
        return department;
    }
    public void setDepartment(String department)
    {
        this.department = department;
    }
}
public class ListToJson {
    public static void main(String[] args)
    {
        try
        {
            List<Employee> employees = new ArrayList<>();
            employees.add(new Employee("Alice", 30, "HR"));
            employees.add(new Employee("Bob", 26, "Finance"));
            employees.add(new Employee("Charlie", 35, "IT"));
            ObjectMapper mapper = new ObjectMapper();
            String jsonArray = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);
            System.out.println("JSON Array Output:\n" + jsonArray);
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
