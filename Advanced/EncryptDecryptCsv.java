import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.node.ArrayNode;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;
import java.util.Iterator;
import java.util.Scanner;
public class EncryptDecryptCsv{
    private static final String SECRET_KEY = "1234567812345678";
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path of input JSON file: ");
        String inputJsonFilePath = scanner.nextLine();
        System.out.print("Enter path of output encrypted CSV file:");
        String encryptedCsvFilePath = scanner.nextLine();
        System.out.print("Enter path of output decrypted CSV file:");
        String decryptedJsonFilePath = scanner.nextLine();
        scanner.close();
        try
        {
            convertJsonToEncryptedCsv(inputJsonFilePath, encryptedCsvFilePath);
            convertEncryptedCsvToJson(encryptedCsvFilePath, decryptedJsonFilePath);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void convertJsonToEncryptedCsv(String jsonFilePath, String csvFilePath) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode jsonTree = jsonMapper.readTree(new File(jsonFilePath));
        if (!jsonTree.isArray() || jsonTree.isEmpty()) {
            throw new IllegalArgumentException("Input JSON must be a non-empty array.");
        }
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        Iterator<String> fieldNames = jsonTree.get(0).fieldNames();
        fieldNames.forEachRemaining(schemaBuilder::addColumn);
        CsvSchema csvSchema = schemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        for (JsonNode node : jsonTree)
        {
            if (node.has("Email"))
                ((ObjectNode) node).put("Email", encrypt(node.get("Email").asText()));
            if (node.has("Salary"))
                ((ObjectNode) node).put("Salary", encrypt(node.get("Salary").asText()));
        }
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File(csvFilePath), jsonTree);

        System.out.println("✅ JSON to Encrypted CSV conversion successful: " + csvFilePath);
    }
    public static void convertEncryptedCsvToJson(String csvFilePath, String jsonFilePath) throws Exception
    {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        ObjectMapper jsonMapper = new ObjectMapper();
        MappingIterator<JsonNode> it = csvMapper.readerFor(JsonNode.class)
                .with(csvSchema)
                .readValues(new File(csvFilePath));
        ArrayNode jsonArray = jsonMapper.createArrayNode();
        while (it.hasNext()) {
            JsonNode row = it.next();
            if (row.has("Email"))
                ((ObjectNode) row).put("Email", decrypt(row.get("Email").asText()));
            if (row.has("Salary"))
                ((ObjectNode) row).put("Salary", decrypt(row.get("Salary").asText()));
            jsonArray.add(row);
        }
        jsonMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(jsonFilePath), jsonArray);

        System.out.println("✅ Encrypted CSV to Decrypted JSON conversion successful: " + jsonFilePath);
    }
    private static String encrypt(String value) throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);
    }
    private static String decrypt(String value) throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(value);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue);
    }
}
