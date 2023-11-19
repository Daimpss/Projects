
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MatchDataReader {

    public List<Match> matchInfo = new ArrayList<>();

    public MatchDataReader(String filePath) {

        try {
            List<String> lines = readLinesFromFile(filePath);

            // Split and process each line
            for (String line : lines) {
                String[] parts = line.split(",");

                // Assuming three values in each line: playerId, action, coinValue, matchId, matchSide
                String matchId = parts[0];
                Float rateA = Float.parseFloat(parts[1]);
                Float rateB = Float.parseFloat(parts[2]);
                String result = parts[3];

                Match match = new Match(matchId, rateA, rateB, EMatchResult.valueOf(result));
                matchInfo.add(match);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<String> readLinesFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllLines(path);
    }
}
