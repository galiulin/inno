package day_16.monkeys;

public class Capucin extends Monkey {
    public int charming;
    protected int harmfull;
    private int footSize;

    public int getTailLenght() {
        return tailLenght;
    }

    public final int tailLenght;

    public int getFootSize() {
        return footSize;
    }

    public Capucin(int charming) {
        this.charming = charming;
        this.footSize = 38;
        this.harmfull = 1;
        this.tailLenght = 100;
    }

    public Capucin(int charming, int harmfull, int footSize) {
        this.charming = charming;
        this.harmfull = harmfull;
        this.footSize = footSize;
        this.tailLenght = 100;
    }

    public void eatsBananas(int bananaCount){
        System.out.println("eating "
                            + bananaCount
                            + " bananas");
    }

    protected void washFace(){
        System.out.println("i have clean Face");
    }

    private void washEars(){
        System.out.println("i have clean Ears");
    }
}
