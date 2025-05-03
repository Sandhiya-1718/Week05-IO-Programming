import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@interface ImportantMethod {
    String level() default "HIGH";
}
class OperationManager {
    @ImportantMethod
    public void initialize()
    {
        System.out.println("Initializing...");
    }
    @ImportantMethod(level = "MEDIUM")
    public void execute()
    {
        System.out.println("Executing...");
    }
    public void cleanup()
    {
        System.out.println("Cleaning up...");
    }
}
public class ImportantMethodTest {
    public static void main(String[] args) throws Exception
    {
        Method[] methods = OperationManager.class.getDeclaredMethods();
        for (Method method : methods)
        {
            if (method.isAnnotationPresent(ImportantMethod.class))
            {
                ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);
                System.out.println("Method: " + method.getName() + ", Level: " + annotation.level());
            }
        }
    }
}