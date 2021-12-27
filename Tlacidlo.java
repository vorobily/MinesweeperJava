public class Tlacidlo extends UIPrvok {
    Text text;
    private int x;
    private int y;
    private int stranaA;
    private int stranaB;
    private Tlacidla identifikator;
    private boolean jeAktivne;

    public Tlacidlo (int stranaA, int stranaB, int x, int y, String text, int velkostTextu, Tlacidla identifikator)  {
        super(stranaA, stranaB, x, y);
        super.zmenFarbu("gray");
        super.zobraz();

        this.x = x;
        this.y = y;
        this.stranaA = stranaA;
        this.stranaB = stranaB;
        this.jeAktivne = true;

        this.identifikator = identifikator;

        this.text = new Text(text, x + velkostTextu / 5, y + velkostTextu, velkostTextu);
    }

    public boolean boloStlacene(int x, int y) {
        return x >= this.x && (this.x + this.stranaA) > x && y >= this.y && (this.y + this.stranaB) > y && this.jeAktivne;
    }

    public Tlacidla getId(){
        return this.identifikator;
    }

    public void zobraz() {
        super.zobraz();
        this.text.zobraz();
        this.jeAktivne = true;
    }

    public void skry() {
        super.skry();
        this.text.skry();
        this.jeAktivne = false;
    }
}
