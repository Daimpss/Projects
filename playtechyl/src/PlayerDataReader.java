
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PlayerDataReader {

    public List<Player> players = new ArrayList<>();
    public List<PlayerAction> actions = new ArrayList<>();
    public PlayerDataReader(String filePath) {

        try {
            List<String> lines = readLinesFromFile(filePath);

            for (String line : lines) {
                String[] parts = line.split(",");

                String playerId = parts[0];
                String action = parts[1];
                String matchId = (parts.length > 2) ? parts[2] : null;
                Integer coinValue = Integer.parseInt(parts[3]);
                String matchSide = (parts.length > 4) ? parts[4] : null;
                if (matchId.length() == 0) {
                    matchId = null;
                    PlayerAction playerAction = new PlayerAction(playerId, EActionType.valueOf(action) , null, coinValue, null);
                    actions.add(playerAction);
                }
                if (PlayerNotInList(playerId) || players.isEmpty()) {
                    players.add(new Player(playerId, null, null, true, 0L, 0.0F, 0, 0));
                }
                if (matchSide != null) {
                    EMatchResult match = EMatchResult.valueOf(matchSide);
                    PlayerAction playerAction = new PlayerAction(playerId, EActionType.valueOf(action) , matchId, coinValue, match);
                    actions.add(playerAction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean PlayerNotInList(String playerId) {
        for (Player player: players) {
            if (player.playerId.equals(playerId)) {
                return false;
            }
        }
        return true;
    }


    private static List<String> readLinesFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllLines(path);
    }
}
