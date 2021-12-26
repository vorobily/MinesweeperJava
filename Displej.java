public class Displej {
    private Segmentovka[] segmentovky;
    private int hodnota;
    private int najvacsiaHodnota;

    public Displej(int poziciaX, int poziciaY, int velkost, int najvacsiaHodnota) {
        this.segmentovky = new Segmentovka[2];
        this.hodnota = 0;
        this.najvacsiaHodnota = najvacsiaHodnota;

        this.segmentovky[0] = new Segmentovka(poziciaX, poziciaY, velkost);
        this.segmentovky[1] = new Segmentovka(poziciaX + velkost / 5 * 8 , poziciaY, velkost);

        this.zobraz(this.hodnota);
    }

    public void zobraz(int cislo) {
        this.hodnota = cislo;
        this.segmentovky[0].zobraz(cislo / 10);
        this.segmentovky[1].zobraz(cislo % 10);
    }

    public boolean pridaj() {
        ++this.hodnota;

        if (this.hodnota >= this.najvacsiaHodnota && this.najvacsiaHodnota != -1) {
            this.zobraz(0);
            return true;
        }

        this.zobraz(this.hodnota);
        return false;
    }

    public void vynuluj() {
        this.zobraz(0);
    }
}
