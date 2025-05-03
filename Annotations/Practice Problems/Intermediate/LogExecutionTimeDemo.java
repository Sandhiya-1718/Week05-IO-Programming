import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@interface LogExecutionTime {}
class TaskRunner {
    @LogExecutionTime
    public void taskOne()
    {
        for (int i = 0; i < 1_000_000; i++) {}
    }
    @LogExecutionTime
    public void taskTwo()
    {
        for (int i = 0; i < 5_000_000; i++) {}
    }
    public void noLogTask()
    {
        System.out.println("This method is not being logged.");
    }
}
public class LogExecutionTimeDemo {
    public static void main(String[] args) throws Exception
    {
        TaskRunner runner = new TaskRunner();
        Class<?> cls = runner.getClass();
        for (Method method : cls.getDeclaredMethods())
        {
            if (method.isAnnotationPresent(LogExecutionTime.class))
            {
                long start = System.nanoTime();
                method.invoke(runner);
                long end = System.nanoTime();
                System.out.println("Executed: " + method.getName() + " | Time Taken: " + (end - start) + " ns");
                System.out.println("-----------------------------");
            }
        }
    }
}
