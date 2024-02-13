public class Displej implements IUIPrvek {
    private Segmentovka[] segmentovky;
    private int hodnota;
    private int nejvissiHodnota;
    private boolean jeViditelny;

    public Displej(int poziceX, int poziceY, int velikost, int nejvissiHodnota) {
        this.segmentovky = new Segmentovka[2];
        this.hodnota = 0;
        this.nejvissiHodnota = nejvissiHodnota;
        this.jeViditelny = true;

        this.segmentovky[0] = new Segmentovka(poziceX, poziceY, velikost);
        this.segmentovky[1] = new Segmentovka(poziceX + velikost / 5 * 8 , poziceY, velikost);

        this.zobraz(this.hodnota);
    }

    public void zobraz() {
        this.jeViditelny = true;
        this.zobraz(this.hodnota);
    }

    public void zobraz(int cislo) {
        if (!this.jeViditelny) {
            return;
        }
        this.hodnota = cislo;
        this.segmentovky[0].zobraz(cislo / 10);
        this.segmentovky[1].zobraz(cislo % 10);
    }

    public boolean pridej() {
        ++this.hodnota;

        if (this.hodnota >= this.nejvissiHodnota && this.nejvissiHodnota != -1) {
            this.zobraz(0);
            return true;
        }

        this.zobraz(this.hodnota);
        return false;
    }

    public void skryt() {
        this.segmentovky[0].zobraz(-1);
        this.segmentovky[1].zobraz(-1);
        this.jeViditelny = false;
    }

    public int getHodnota() {
        return this.hodnota;
    }
}
