import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
public class CreateStudentJson {
    public static void main(String[] args)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode student = objectMapper.createObjectNode();
            student.put("name", "John Doe");
            student.put("age", 21);
            ArrayNode subjects = objectMapper.createArrayNode();
            subjects.add("Math");
            subjects.add("Science");
            subjects.add("English");
            student.set("subjects", subjects);
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
            System.out.println(jsonString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
