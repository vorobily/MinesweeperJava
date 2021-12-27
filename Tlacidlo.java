public class Tlacidlo extends UIPrvok {
    Text text;
    private int x;
    private int y;
    private int stranaA;
    private int stranaB;
    private Tlacidla identifikator;

    public Tlacidlo (int stranaA, int stranaB, int x, int y, String text, int velkostTextu, Tlacidla identifikator)  {
        super(stranaA, stranaB, x, y);
        super.zmenFarbu("gray");
        super.zobraz();

        this.x = x;
        this.y = y;
        this.stranaA = stranaA;
        this.stranaB = stranaB;

        this.identifikator = identifikator;

        this.text = new Text(text, x + velkostTextu / 5, y + velkostTextu, velkostTextu);
    }

    public boolean obsahujeSuradnice(int x, int y) {
        return x >= this.x && (this.x + this.stranaA) > x && y >= this.y && (this.y + this.stranaB) > y;
    }

    public Tlacidla getId(){
        return this.identifikator;
    }
}
