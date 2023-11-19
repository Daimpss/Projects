import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class ProgramEngine {

    public List<Player> players;
    public List<PlayerAction> actions;
    List<Match> matches;
    Long casinoBalance = 0L;


    public ProgramEngine(List<Player> players, List<PlayerAction> actions, List<Match> matches) {

        this.players = players;
        this.actions = actions;
        this.matches = matches;

        for (Player player : players) {
            if (player.isLegalPlayer) {
                for (PlayerAction action : actions) {
                    if (player.playerId.equals(action.playerId)) {
                        HandleAction(action, player);
                    }
                }
            }
        }

    }


    public void HandleAction(PlayerAction action, Player player) {
        if (action.playerOperation.equals(EActionType.DEPOSIT)) {
            player.balance = player.balance + action.coinValue;
            player.actions.add(action);
        }
        else if (action.playerOperation.equals(EActionType.WITHDRAW)) {
            if (IsLegalMove(action, player)) {
                player.balance = player.balance - action.coinValue;
                player.actions.add(action);
            }
            else {
                player.isLegalPlayer = false;
                player.actions.add(action);
            }
        }
        else {
            // Player's operation is BET!
            if (player.isLegalPlayer) {
                if (IsLegalMove(action, player)) {
                    for (Match match: matches) {
                        if (match.matchId.equals(action.matchId)) {
                            if (match.result.equals(action.matchSide)) {
                                //The player has won!
                                HandleBetWin(player, action, match);
                                break;
                            }
                            else if (match.result.equals(EMatchResult.DRAW)) {
                                HandleBetDraw(player, action);
                                break;
                            } else {
                                //Player has lost the bet.
                                player.balance = player.balance - action.coinValue;
                                HandleBetLose(player, action);
                                break;
                            }
                        }
                    }
                }
                else {
                    player.isLegalPlayer = false;
                    player.actions.add(action);
                }
            }
            else {
                System.out.println(casinoBalance);
                player.isLegalPlayer = false;
                player.actions.add(action);
            }
        }
    }


    public boolean IsLegalMove(PlayerAction action, Player player) {

        //Handle betting
        if (action.playerOperation.equals(EActionType.BET)) {
            return action.coinValue <= player.balance;
        } else if (action.playerOperation.equals(EActionType.WITHDRAW)) {
            return action.coinValue <= player.balance;
        }
        return false;
    }


    public void HandleBetWin(Player player, PlayerAction action, Match match) {
        player.gamesWon++;
        player.gamesPlayed++;
        player.actions.add(action);
        if (action.matchSide.equals(EMatchResult.A)) {
            player.balance = (long) Math.floor(player.balance + action.coinValue + match.rateA * action.coinValue);
            player.bets.add(new Bet((long) Math.floor(-match.rateA * action.coinValue)));
        } else if (action.matchSide.equals(EMatchResult.B)) {
            player.balance = (long) Math.floor(player.balance + action.coinValue + match.rateB * action.coinValue);
            player.bets.add(new Bet((long) Math.floor(-match.rateA * action.coinValue)));
        }
    }

    public void HandleBetDraw(Player player, PlayerAction action) {
        player.actions.add(action);
        player.gamesPlayed++;
    }

    public void HandleBetLose(Player player, PlayerAction action) {
        player.actions.add(action);
        player.bets.add(new Bet((long) action.coinValue));
        player.gamesPlayed++;
    }

    public List<Player> GetLegalPlayers(List<Player> players) {
        return players.stream().filter(player -> player.isLegalPlayer).collect(Collectors.toList());
    }

    public List<PlayerAction> GetIllegalActions(List<Player> players) {
        return players.stream()
                .filter(player -> !player.isLegalPlayer)
                .flatMap(illegalPlayer -> {
                    if (!illegalPlayer.actions.isEmpty()) {
                        PlayerAction lastAction = illegalPlayer.actions.get(illegalPlayer.actions.size() - 1);
                        return Stream.of(lastAction);
                    } else {
                        return Stream.empty();
                    }
                })
                .collect(Collectors.toList());
    }

    public Long GetCasinoBalance (List<Player> players) {
        var result = 0L;
        for (Player player: players) {
            if (player.isLegalPlayer) {
                for (Bet bet: player.bets) {
                    result += bet.amount;
                }
            }
        }
        return result;
    }
}
