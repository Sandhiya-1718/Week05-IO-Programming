import java.lang.reflect.Field;
class Person {
    private int age;
    public Person(int age)
    {
        this.age = age;
    }
}
public class AccessPrivateFieldDemo {
    public static void main(String[] args)
    {
        try
        {
            Person person = new Person(25);
            System.out.println("Original Age: " + person);
            Field ageField = Person.class.getDeclaredField("age");
            ageField.setAccessible(true);
            int age = (int) ageField.get(person);
            System.out.println("Retrieved Age: " + age);
            ageField.set(person, 30);
            System.out.println("Updated Person Age: " + person);
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
