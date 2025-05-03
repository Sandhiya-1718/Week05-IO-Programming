import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
@interface Author
{
    String name();
}
@Author(name = "Lily")
class MyClass {
}
public class AuthorAnnotationDemo {
    public static void main(String[] args)
    {
        Class<MyClass> cls = MyClass.class;
        if (cls.isAnnotationPresent(Author.class))
        {
            Author author = cls.getAnnotation(Author.class);
            System.out.println("Author: " + author.name());
        }
        else
            System.out.println("No @Author annotation found.");
    }
}
