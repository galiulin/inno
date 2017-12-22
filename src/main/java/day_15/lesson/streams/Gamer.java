package day_15.lesson.streams;

public class Gamer {
    private long weight;
    private boolean isProf = false;
    private short level;
    private String nickName;
    private boolean isVirgin = true;
    private GameType gameType;

    @Override
    public String toString() {
        return "Gamer{" +
                "weight=" + weight +
                ", isProf=" + isProf +
                ", level=" + level +
                ", nickName='" + nickName + '\'' +
                ", isVirgin=" + isVirgin +
                ", gameType=" + gameType +
                '}';
    }

    public Gamer(long weight, boolean isProf, short level, String nickName, boolean isVirgin, GameType gameType) {
        this.weight = weight;
        this.isProf = isProf;
        this.level = level;
        this.nickName = nickName;
        this.isVirgin = isVirgin;
        this.gameType = gameType;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public boolean isProf() {
        return isProf;
    }

    public void setProf(boolean prof) {
        isProf = prof;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isVirgin() {
        return isVirgin;
    }

    public void setVirgin(boolean virgin) {
        isVirgin = virgin;
    }
}
