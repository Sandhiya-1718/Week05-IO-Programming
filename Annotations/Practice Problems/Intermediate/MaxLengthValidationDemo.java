import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
@Retention(RetentionPolicy.RUNTIME)
@interface MaxLength {
    int value();
}
class User {
    @MaxLength(10)
    private String username;
    public User(String username)
    {
        this.username = username;
        validateMaxLength();
    }
    private void validateMaxLength()
    {
        Class<?> cls = this.getClass();
        for (Field field : cls.getDeclaredFields())
        {
            if (field.isAnnotationPresent(MaxLength.class))
            {
                field.setAccessible(true);
                MaxLength maxLength = field.getAnnotation(MaxLength.class);
                try
                {
                    Object value = field.get(this);
                    if (value instanceof String str && str.length() > maxLength.value())
                    {
                        throw new IllegalArgumentException(
                                "Field '" + field.getName() + "' exceeds max length of " + maxLength.value());
                    }
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
public class MaxLengthValidationDemo {
    public static void main(String[] args)
    {
        try
        {
            User user1 = new User("Bharath");
            System.out.println("User 1 created successfully.");
            User user2 = new User("Meenakshi Sundaram");
            System.out.println("User 2 created successfully.");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }
}
