import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
@Retention(RetentionPolicy.RUNTIME)
@interface Inject {}
class Service {
    public void serve()
    {
        System.out.println("Service is running...");
    }
}
class Client {
    @Inject
    private Service service;
    public void doWork()
    {
        service.serve();
    }
}
class SimpleDIContainer {
    public static void injectDependencies(Object obj)
    {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields())
        {
            if (field.isAnnotationPresent(Inject.class))
            {
                try
                {
                    field.setAccessible(true);
                    Object dependency = field.getType().getDeclaredConstructor().newInstance();
                    field.set(obj, dependency);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
public class SimpleDiContainerDemo {
    public static void main(String[] args)
    {
        Client client = new Client();
        SimpleDIContainer.injectDependencies(client);
        client.doWork();
    }
}
