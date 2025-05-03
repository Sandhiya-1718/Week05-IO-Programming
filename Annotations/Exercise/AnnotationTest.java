import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@interface TaskInfo {
    String priority();
    String assignedTo();
}
class TaskManager {
    @TaskInfo(priority = "High", assignedTo = "Alice")
    public void completeTask()
    {
        System.out.println("Task completed.");
    }
}
public class AnnotationTest {
    public static void main(String[] args) throws Exception
    {
        Method method = TaskManager.class.getMethod("completeTask");
        if (method.isAnnotationPresent(TaskInfo.class))
        {
            TaskInfo info = method.getAnnotation(TaskInfo.class);
            System.out.println("Priority: " + info.priority());
            System.out.println("Assigned To: " + info.assignedTo());
        }
    }
}