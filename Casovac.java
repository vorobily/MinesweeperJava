public class Casovac {
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
}
