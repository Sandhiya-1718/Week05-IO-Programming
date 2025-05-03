import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.StringJoiner;
@Retention(RetentionPolicy.RUNTIME)
@interface JsonField
{
    String name();
}
class User {
    @JsonField(name = "user_name")
    private String username;
    @JsonField(name = "user_email")
    private String email;
    @JsonField(name = "user_age")
    private int age;
    public User(String username, String email, int age)
    {
        this.username = username;
        this.email = email;
        this.age = age;
    }
}
class JsonSerializer {
    public static String toJson(Object obj)
    {
        Class<?> cls = obj.getClass();
        StringJoiner json = new StringJoiner(", ", "{", "}");
        for (Field field : cls.getDeclaredFields())
        {
            if (field.isAnnotationPresent(JsonField.class))
            {
                JsonField jsonField = field.getAnnotation(JsonField.class);
                field.setAccessible(true);
                try
                {
                    Object value = field.get(obj);
                    String key = jsonField.name();
                    String val = (value instanceof String) ? "\"" + value + "\"" : String.valueOf(value);
                    json.add("\"" + key + "\":" + val);
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return json.toString();
    }
}
public class CustomJsonSerializerDemo {
    public static void main(String[] args)
    {
        User user = new User("lily", "lily007@gmali.com", 22);
        String json = JsonSerializer.toJson(user);
        System.out.println("Serialized JSON:");
        System.out.println(json);
    }
}
