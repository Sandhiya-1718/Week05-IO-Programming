import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(BugReports.class)
@interface BugReport {
    String description();
}
@Retention(RetentionPolicy.RUNTIME)
@interface BugReports {
    BugReport[] value();
}
class BugTracker {
    @BugReport(description = "Null pointer when input is null")
    @BugReport(description = "Incorrect output on edge cases")
    public void process()
    {
        System.out.println("Processing...");
    }
}
public class BugReportTest {
    public static void main(String[] args) throws Exception
    {
        Method method = BugTracker.class.getMethod("process");
        BugReport[] reports = method.getAnnotationsByType(BugReport.class);
        for (BugReport report : reports)
            System.out.println("Bug: " + report.description());
    }
}
