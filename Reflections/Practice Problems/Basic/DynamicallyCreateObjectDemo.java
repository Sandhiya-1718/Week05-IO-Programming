import java.lang.reflect.Constructor;
class Student {
    private String name;
    private int age;
    public Student(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    public void display()
    {
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
    }
}
public class DynamicallyCreateObjectDemo {
    public static void main(String[] args)
    {
        try
        {
            Class<?> studentClass = Class.forName("Student");
            Constructor<?> constructor = studentClass.getConstructor(String.class, int.class);
            Student student = (Student) constructor.newInstance("Alice", 20);
            student.display();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

