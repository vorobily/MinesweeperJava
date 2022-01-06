public abstract class UIPrvok implements IUIPrvok {
    private Obdlznik obdlznik;
    
    public UIPrvok(int stranaA, int stranaB, int x, int y) {
        this.obdlznik = new Obdlznik(stranaA, stranaB, x, y);
    }

    public void zmenPolohu(int x, int y) {
        this.obdlznik.zmenPolohu(x, y);
    }

    public void zmenStrany(int stranaA, int stranaB) {
        this.obdlznik.zmenStrany(stranaA, stranaB);
    }

    public void zmenFarbu(String farba) {
        this.obdlznik.zmenFarbu(farba);
    }

    public void zobraz() {
        this.obdlznik.zobraz();
    }

    public void skry() {
        this.obdlznik.skry();
    }
}
