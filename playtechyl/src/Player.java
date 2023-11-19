import java.util.ArrayList;
import java.util.List;

public class Player {

    public String playerId;
    public List<PlayerAction> actions;

    public List<Bet> bets;

    public Boolean isLegalPlayer = true;

    public Long balance;
    public Float winRate;

    public Integer gamesPlayed;

    public Integer gamesWon;

    public Player(String playerId, List<PlayerAction> actions, List<Bet> bets, Boolean isLegalPlayer, Long balance, Float winRate, Integer gamesPlayed, Integer gamesWon) {

        this.playerId = playerId;
        this.actions = new ArrayList<>();
        this.bets = new ArrayList<>();
        this.isLegalPlayer = isLegalPlayer;
        this.balance = balance;
        this.winRate = winRate;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
    }

    @Override
    public String toString() {
        return playerId + " " +
                balance + " " +
                winRate;
    }
}
