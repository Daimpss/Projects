

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter {
    public DataWriter(String filePath, List<Player> legalPlayers, List<PlayerAction> illegalActions, Long casinoBalance) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            WriteLegalPlayers(legalPlayers, writer);

            WriteIllegalActions(illegalActions, writer);

            writer.write(String.valueOf(casinoBalance));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void WriteIllegalActions(List<PlayerAction> illegalActions, BufferedWriter writer) throws IOException {
        if (!illegalActions.isEmpty()) {
            for (PlayerAction action: illegalActions) {
                if (illegalActions.size() > 1 && !action.playerId.equals(illegalActions.get(illegalActions.size() - 1).playerId)) {
                    writer.write(action + "; ");
                }
                else {
                    writer.write(action.toString());
                }
            }
        }
        writer.newLine();
        writer.newLine();
    }

    private void WriteLegalPlayers(List<Player> legalPlayers, BufferedWriter writer) throws IOException {
        if (!legalPlayers.isEmpty()) {
            for (Player player: legalPlayers) {
                player.winRate = (float) player.gamesWon / player.gamesPlayed;
                player.winRate = Float.parseFloat(String.format("%.2f", player.winRate));
                if (legalPlayers.size() > 1 && !player.playerId.equals(legalPlayers.get(legalPlayers.size() - 1).playerId)) {
                    writer.write(player + "; ");
                }
                else {
                    writer.write(player.toString());
                }
            }
        }
        writer.newLine();
        writer.newLine();
    }
}
