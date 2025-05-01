import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.File;
import java.io.IOException;
import java.util.*;
class Match {
    public int match_id;
    public String team1;
    public String team2;
    public Map<String, Integer> score;
    public String winner;
    public String player_of_match;
}
public class IplCensorAnalyzer {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path for input JSON file: ");
        String jsonInputPath = scanner.nextLine();
        System.out.print("Enter path for input CSV file: ");
        String csvInputPath = scanner.nextLine();
        System.out.print("Enter path to save censored JSON file: ");
        String jsonOutputPath = scanner.nextLine();
        System.out.print("Enter path to save censored CSV file: ");
        String csvOutputPath = scanner.nextLine();
        scanner.close();
        try
        {
            List<Match> matches = readJson(jsonInputPath);
            List<Match> csvMatches = readCsv(csvInputPath);
            applyCensorship(matches);
            applyCensorship(csvMatches);
            writeJson(matches, jsonOutputPath);
            writeCsv(csvMatches, csvOutputPath);
            System.out.println("Censored data written successfully.");
        }
        catch (Exception e)
        {
            System.out.println("Error occurred:\n" + e);
        }
    }
    static List<Match> readJson(String filePath) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), new TypeReference<>() {
        });
    }
    static void writeJson(List<Match> matches, String filePath) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), matches);
    }
    static List<Match> readCsv(String filePath) throws IOException
    {
        List<Match> matches = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(filePath)))
        {
            if (sc.hasNextLine())
                sc.nextLine();
            while (sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] parts = line.split(",", -1); // Split CSV columns
                Match match = new Match();
                match.match_id = Integer.parseInt(parts[0]);
                match.team1 = parts[1];
                match.team2 = parts[2];
                Map<String, Integer> score = new LinkedHashMap<>();
                if (!parts[3].isEmpty())
                {
                    String[] scores = parts[3].split(";");
                    for (String s : scores)
                    {
                        String[] kv = s.split("=");
                        score.put(kv[0], Integer.parseInt(kv[1]));
                    }
                }
                match.score = score;
                match.winner = parts[4];
                match.player_of_match = parts[5];
                matches.add(match);
            }
        }
        return matches;
    }
    static void writeCsv(List<Match> matches, String filePath) throws IOException
    {
        try (Formatter formatter = new Formatter(filePath))
        {
            formatter.format("match_id,team1,team2,score,winner,player_of_match%n");
            for (Match match : matches)
            {
                StringBuilder scoreBuilder = new StringBuilder();
                for (Map.Entry<String, Integer> entry : match.score.entrySet())
                {
                    if (scoreBuilder.length() > 0) scoreBuilder.append(";");
                    scoreBuilder.append(entry.getKey()).append("=").append(entry.getValue());
                }
                formatter.format(
                        "%d,%s,%s,%s,%s,%s%n",
                        match.match_id,
                        match.team1,
                        match.team2,
                        scoreBuilder.toString(),
                        match.winner,
                        match.player_of_match
                );
            }
        }
    }
    static void applyCensorship(List<Match> matches)
    {
        for (Match match : matches) {
            match.team1 = censorTeam(match.team1);
            match.team2 = censorTeam(match.team2);
            match.winner = censorTeam(match.winner);
            match.player_of_match = "REDACTED";
            if (match.score != null)
            {
                Map<String, Integer> newScore = new LinkedHashMap<>();
                for (Map.Entry<String, Integer> entry : match.score.entrySet())
                    newScore.put(censorTeam(entry.getKey()), entry.getValue());
                match.score = newScore;
            }
        }
    }
    static String censorTeam(String teamName)
    {
        if (teamName == null)
            return null;
        String[] parts = teamName.split(" ");
        return parts[0] + " ***";
    }
}
