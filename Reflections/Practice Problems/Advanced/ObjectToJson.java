import java.lang.reflect.Field;
class Person {
    private String name = "Lily";
    private int age = 22;
    private boolean active = true;
}
public class ObjectToJson {
    public static String toJson(Object obj)
    {
        StringBuilder json = new StringBuilder("{");
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try
        {
            for (int i = 0; i < fields.length; i++)
            {
                Field field = fields[i];
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(obj);
                json.append("\"").append(name).append("\": ");
                if (value instanceof String)
                    json.append("\"").append(value).append("\"");
                else
                    json.append(value);
                if (i < fields.length - 1)
                    json.append(", ");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        json.append("}");
        return json.toString();
    }
    public static void main(String[] args)
    {
        Person p = new Person();
        String jsonOutput = toJson(p);
        System.out.println(jsonOutput);
    }
}
