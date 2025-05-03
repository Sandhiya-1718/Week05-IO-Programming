import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
@Retention(RetentionPolicy.RUNTIME)
@interface CacheResult {}
class ExpensiveService {
    private Map<Integer, Integer> cache = new HashMap<>();
    @CacheResult
    public int computeSquare(int num)
    {
        if (cache.containsKey(num))
        {
            System.out.println("Returning cached result for " + num);
            return cache.get(num);
        }
        System.out.println("Computing square of " + num);
        int result = num * num;
        cache.put(num, result);
        return result;
    }
}
public class CustomCacheDemo {
    public static void main(String[] args) throws Exception
    {
        ExpensiveService service = new ExpensiveService();
        Method method = service.getClass().getMethod("computeSquare", int.class);
        if (method.isAnnotationPresent(CacheResult.class))
        {
            System.out.println("First call: " + service.computeSquare(5));
            System.out.println("Second call: " + service.computeSquare(5));
            System.out.println("Third call: " + service.computeSquare(7));
            System.out.println("Fourth call: " + service.computeSquare(7)); 
        }
    }
}
