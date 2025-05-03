import java.lang.reflect.Method;
class Calculator {
    private int multiply(int a, int b)
    {
        return a * b;
    }
}
public class InvokePrivateMethodDemo {
    public static void main(String[] args)
    {
        try
        {
            Calculator calculator = new Calculator();
            Method multiplyMethod = Calculator.class.getDeclaredMethod("multiply", int.class, int.class);
            multiplyMethod.setAccessible(true);
            int result = (int) multiplyMethod.invoke(calculator, 3, 4);
            System.out.println("Multiplication Result: " + result);
        }
        catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
}
