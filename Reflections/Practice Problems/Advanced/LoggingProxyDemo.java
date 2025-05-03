import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
interface Greeting
{
    void sayHello(String name);
}
class GreetingImpl implements Greeting {
    public void sayHello(String name)
    {
        System.out.println("Hello, " + name + "!");
    }
}
class LoggingHandler implements InvocationHandler
{
    private Object target;
    public LoggingHandler(Object target)
    {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println("[LOG] Calling method: " + method.getName());
        return method.invoke(target, args);
    }
}
public class LoggingProxyDemo {
    public static void main(String[] args)
    {
        Greeting original = new GreetingImpl();
        Greeting proxy = (Greeting) Proxy.newProxyInstance(
                Greeting.class.getClassLoader(),
                new Class[] { Greeting.class },
                new LoggingHandler(original)
        );
        proxy.sayHello("Lily");
    }
}
