import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;
public class ClassInfoExplorer {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter full class name (e.g., java.util.ArrayList): ");
        String className = scanner.nextLine();
        try
        {
            Class<?> cls = Class.forName(className);
            System.out.println("\n--- Class: " + cls.getName() + " ---");
            System.out.println("\nFields:");
            Field[] fields = cls.getDeclaredFields();
            if (fields.length == 0)
                System.out.println("No fields found.");
            else
            {
                for (Field f : fields)
                    System.out.println(f);
            }
            System.out.println("\nMethods:");
            Method[] methods = cls.getDeclaredMethods();
            if (methods.length == 0)
                System.out.println("No methods found.");
            else
            {
                for (Method m : methods)
                    System.out.println(m);
            }
            System.out.println("\nConstructors:");
            Constructor<?>[] constructors = cls.getDeclaredConstructors();
            if (constructors.length == 0)
                System.out.println("No constructors found.");
            else
            {
                for (Constructor<?> c : constructors)
                    System.out.println(c);
            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class not found: " + className);
        }
    }
}
