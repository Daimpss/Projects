public class PlayerAction {

    public String playerId;
    public EActionType playerOperation;
    public String matchId;
    public Integer coinValue;
    public EMatchResult matchSide;

    public PlayerAction(String playerId, EActionType playerOperation, String matchId, Integer coinValue, EMatchResult matchSide) {
        this.playerId = playerId;
        this.playerOperation = playerOperation;
        this.matchId = matchId;
        this.coinValue = coinValue;
        this.matchSide = matchSide;
    }


    @Override
    public String toString() {
        return playerId + " " +
                playerOperation + " " +
                matchId + " " +
                coinValue + " " + matchSide;
    }
}


