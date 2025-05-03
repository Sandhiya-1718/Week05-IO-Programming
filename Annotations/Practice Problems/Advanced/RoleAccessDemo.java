import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@interface RoleAllowed
{
    String value();
}
class SecureOperations {
    @RoleAllowed("ADMIN")
    public void deleteUserAccount()
    {
        System.out.println("User account deleted successfully!");
    }
    @RoleAllowed("USER")
    public void viewDashboard()
    {
        System.out.println("Dashboard displayed.");
    }
}
class UserContext {
    String currentRole;
    public UserContext(String role)
    {
        this.currentRole = role;
    }
    public String getRole()
    {
        return currentRole;
    }
}
public class RoleAccessDemo {
    public static void main(String[] args) throws Exception
    {
        UserContext user = new UserContext("USER");
        SecureOperations ops = new SecureOperations();
        Class<?> cls = ops.getClass();
        for (Method method : cls.getDeclaredMethods())
        {
            if (method.isAnnotationPresent(RoleAllowed.class))
            {
                RoleAllowed roleAnnotation = method.getAnnotation(RoleAllowed.class);
                String allowedRole = roleAnnotation.value();
                System.out.println("Trying to execute: " + method.getName());
                if (user.getRole().equals(allowedRole))
                    method.invoke(ops);
                else
                    System.out.println("Access Denied! Allowed Role: " + allowedRole);
                System.out.println("-----------------------------");
            }
        }
    }
}
