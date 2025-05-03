import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
class Task {
    public void fastTask()
    {
        for (int i = 0; i < 1000; i++) {}
    }
    public void slowTask()
    {
        try
        {
            Thread.sleep(300);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
public class MethodTimingDemo {
    public static void main(String[] args)
    {
        try
        {
            Class<?> clazz = Task.class;
            Object obj = clazz.getDeclaredConstructor().newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods)
            {
                if (method.getParameterCount() == 0)
                {
                    long start = System.nanoTime();
                    method.invoke(obj);
                    long end = System.nanoTime();
                    long duration = end - start;
                    System.out.println("Method: " + method.getName() + " executed in " + duration / 1_000_000.0 + " ms");
                }
            }
        }
        catch (InstantiationException | IllegalAccessException |
                 InvocationTargetException | NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }
}
