public class Casovac implements IUIPrvok {
    private Displej[] displeje;

    public Casovac(int polohaX, int polohaY, int velkost) {
        this.displeje = new Displej[2];

        this.displeje[0] = new Displej(polohaX, polohaY, velkost, -1);
        this.displeje[1] = new Displej(polohaX + velkost / 5 * 18, polohaY, velkost, 60);
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
    }

    public void zobraz() {
        this.displeje[0].zobraz();
        this.displeje[1].zobraz();
    }

    public int[] getCas() {
        return new int[] {this.displeje[0].getHodnota(), this.displeje[1].getHodnota()};
    }

    public String getFromatovanyCas() {
        int[] cas = this.getCas();
        return String.format("%s:%s", Casovac.getHodnotaSNulou(cas[0]), Casovac.getHodnotaSNulou(cas[1]));
    }

    public static String getHodnotaSNulou(int hodnota) {
        if (hodnota < 10) {
            return "0" + String.valueOf(hodnota);
        }
        return String.valueOf(hodnota);
    }
}
