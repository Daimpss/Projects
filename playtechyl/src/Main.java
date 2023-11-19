public class Main {

    public static void main(String[] args) {
        // Read in the information from the player_data.txt.
        var playerDataFilePath = "src/player_data.txt";
        PlayerDataReader playerReader = new PlayerDataReader(playerDataFilePath);

        // Read in the information from the match_data.txt.
        var matchDataFilePath = "src/match_data.txt";
        MatchDataReader matchReader = new MatchDataReader(matchDataFilePath);

        // All the logic.
        ProgramEngine gameEngine = new ProgramEngine(playerReader.players, playerReader.actions, matchReader.matchInfo);

        // Write to the result2.txt.
        var legalPlayers = gameEngine.GetLegalPlayers(gameEngine.players);
        var illegalActions = gameEngine.GetIllegalActions(gameEngine.players);
        var casinoBalance = gameEngine.GetCasinoBalance(gameEngine.players);
        var resultPath = "src/result2.txt";
        new DataWriter(resultPath, legalPlayers, illegalActions, casinoBalance);

    }

}