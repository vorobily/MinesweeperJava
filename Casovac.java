public class Casovac implements IUIPrvek {
    private Displej[] displeje;
    private Segment[] segmenty;

    public Casovac(int polohaX, int polohaY, int velikost) {
        this.displeje = new Displej[2];
        this.segmenty = new Segment[2];

        /* Čísla */
        this.displeje[0] = new Displej(polohaX, polohaY, velikost, -1);
        this.displeje[1] = new Displej(polohaX + velikost / 5 * 18, polohaY, velikost, 60);

        /* Body */
        this.segmenty[0] = new Segment(velikost / 5, velikost / 5, polohaX + velikost / 5 * 16, polohaY + velikost / 5 * 4);
        this.segmenty[1] = new Segment(velikost / 5, velikost / 5, polohaX + velikost / 5 * 16, polohaY + velikost / 5 * 8);

        this.segmenty[0].zobraz();
        this.segmenty[1].zobraz();
    }

    public void pridej() {
        if (this.displeje[1].pridej()) {
            this.displeje[0].pridej();
        }
    }

    public void vynuluj() {
        this.displeje[0].zobraz(0);
        this.displeje[1].zobraz(0);
    }
    
    public void skryt() {
        this.displeje[0].skryt();
        this.displeje[1].skryt();

        this.segmenty[0].skryt();
        this.segmenty[1].skryt();
    }

    public void zobraz() {
        this.displeje[0].zobraz();
        this.displeje[1].zobraz();

        this.segmenty[0].zobraz();
        this.segmenty[1].zobraz();
    }

    public int[] getCas() {
        return new int[] {this.displeje[0].getHodnota(), this.displeje[1].getHodnota()};
    }

    public String getFormatovanyCas() {
        int[] cas = this.getCas();
        return String.format("%s:%s", Operace.getHodnotaSNulou(cas[0]), Operace.getHodnotaSNulou(cas[1]));
    }
}
