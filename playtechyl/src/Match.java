public class Match {
    public String matchId;
    public Float rateA;
    public Float rateB;
    public EMatchResult result;

    public Match(String matchId, Float rateA, Float rateB, EMatchResult result) {

        this.matchId = matchId;
        this.rateA = rateA;
        this.rateB = rateB;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Match{" +
                "MatchId='" + matchId + '\'' +
                ", A_rate='" + rateA + '\'' +
                ", B_rate='" + rateB + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

}
