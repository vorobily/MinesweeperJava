public class Tlacidlo extends UIPrvek {
    private Text text;
    private int x;
    private int y;
    private int stranaA;
    private int stranaB;
    private Tlacidla identifikator;
    private boolean jeAktivne;

    public Tlacidlo (int stranaA, int stranaB, int x, int y, String text, int velikostTextu, Tlacidla identifikator)  {
        super(stranaA, stranaB, x, y);
        super.zmenBarvu("gray");
        super.zobraz();

        this.x = x;
        this.y = y;
        this.stranaA = stranaA;
        this.stranaB = stranaB;
        this.jeAktivne = true;

        this.identifikator = identifikator;

        this.text = new Text(text, x + velikostTextu / 5, y + velikostTextu, velikostTextu);
    }

    public boolean byloStlaceno(int x, int y) {
        return x >= this.x && (this.x + this.stranaA) > x && y >= this.y && (this.y + this.stranaB) > y && this.jeAktivne;
    }

    public Tlacidla getId() {
        return this.identifikator;
    }

    @Override
    public void zobraz() {
        super.zobraz();
        this.text.zobraz();
        this.jeAktivne = true;
    }

    @Override
    public void skryt() {
        super.skryt();
        this.text.skryt();
        this.jeAktivne = false;
    }
}
