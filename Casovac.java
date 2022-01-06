public class Casovac implements IUIPrvok {
    private Displej[] displeje;
    private Segment[] segmenty;

    public Casovac(int polohaX, int polohaY, int velkost) {
        this.displeje = new Displej[2];
        this.segmenty = new Segment[2];

        /* Čísla */
        this.displeje[0] = new Displej(polohaX, polohaY, velkost, -1);
        this.displeje[1] = new Displej(polohaX + velkost / 5 * 18, polohaY, velkost, 60);

        /* Bodky */
        this.segmenty[0] = new Segment(velkost / 5, velkost / 5, polohaX + velkost / 5 * 16, polohaY + velkost / 5 * 4);
        this.segmenty[1] = new Segment(velkost / 5, velkost / 5, polohaX + velkost / 5 * 16, polohaY + velkost / 5 * 8);

        this.segmenty[0].zobraz();
        this.segmenty[1].zobraz();
    }

    public void pridaj() {
        if (this.displeje[1].pridaj()) {
            this.displeje[0].pridaj();
        }
    }

    public void vynuluj() {
        this.displeje[0].zobraz(0);
        this.displeje[1].zobraz(0);
    }
    
    public void skry() {
        this.displeje[0].skry();
        this.displeje[1].skry();

        this.segmenty[0].skry();
        this.segmenty[1].skry();
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

    public String getFromatovanyCas() {
        int[] cas = this.getCas();
        return String.format("%s:%s", Operacie.getHodnotaSNulou(cas[0]), Operacie.getHodnotaSNulou(cas[1]));
    }
}
