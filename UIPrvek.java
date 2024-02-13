public abstract class UIPrvek implements IUIPrvek {
    private Obdelnik obdelnik;
    
    public UIPrvek(int stranaA, int stranaB, int x, int y) {
        this.obdelnik = new Obdelnik(stranaA, stranaB, x, y);
    }

    public void zmenBarvu(String barva) {
        this.obdelnik.zmenBarva(barva);
    }

    public void zobraz() {
        this.obdelnik.zobraz();
    }

    public void skryt() {
        this.obdelnik.skryt();
    }
}
