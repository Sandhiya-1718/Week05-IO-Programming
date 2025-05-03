import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}
class ProjectTasks {
    @Todo(task = "Implement login feature", assignedTo = "John")
    public void login()
    {
        System.out.println("Login task placeholder");
    }
    @Todo(task = "Optimize database queries", assignedTo = "Emma", priority = "HIGH")
    public void optimizeDB()
    {
        System.out.println("Database optimization placeholder");
    }
    public void completedFeature()
    {
        System.out.println("This feature is already done.");
    }
}
public class ToDoAnnotation {
    public static void main(String[] args) throws Exception
    {
        Method[] methods = ProjectTasks.class.getDeclaredMethods();
        for (Method method : methods)
        {
            if (method.isAnnotationPresent(Todo.class))
            {
                Todo todo = method.getAnnotation(Todo.class);
                System.out.println("Method: " + method.getName());
                System.out.println("  Task: " + todo.task());
                System.out.println("  Assigned To: " + todo.assignedTo());
                System.out.println("  Priority: " + todo.priority());
            }
        }
    }
}
