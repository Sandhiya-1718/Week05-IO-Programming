import java.lang.reflect.Field;
import java.util.Map;
class User {
    public String name;
    public int age;
    public boolean active;
    public void display()
    {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Active: " + active);
    }
}
public class CustomObjectMapperDemo {
    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) {
        try
        {
            T obj = clazz.getDeclaredConstructor().newInstance();
            for (Map.Entry<String, Object> entry : properties.entrySet())
            {
                String fieldName = entry.getKey();
                Object value = entry.getValue();
                try
                {
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(obj, value);
                }
                catch (NoSuchFieldException e)
                {
                }
            }
            return obj;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to create object", e);
        }
    }
    public static void main(String[] args)
    {
        Map<String, Object> userData = Map.of(
                "name", "John",
                "age", 22,
                "active", true
        );
        User user = toObject(User.class, userData);
        user.display();
    }
}
