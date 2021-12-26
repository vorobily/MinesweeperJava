public class Policko extends UIPrvok{
    private ObsahPolicka obsahPolicka;
    private Obrazok obrazok;

    private int velkostStrany;
    private int x;
    private int y;
    
    public Policko(int velkostStrany, int x, int y) {
        super(velkostStrany, velkostStrany, x, y);

        this.obsahPolicka = ObsahPolicka.VOLNE;

        this.velkostStrany = velkostStrany;
        this.x = x;
        this.y = y;

        super.zmenFarbu("white");
        super.zobraz();

        nastavCislo(1);
    }

    public boolean obsahujeSuradnice(int x, int y) {
        return x >= this.x && (this.x + this.velkostStrany) > x && y >= this.y && (this.y + this.velkostStrany) > y;
    }

    public void nastavObrazok(Obrazky obrazky) {
        if (this.obrazok != null) {
            this.obrazok.skry();
        }

        if (obrazky == null) {
            return;
        }
        
        this.obrazok = new Obrazok(obrazky.getCesta());
        this.obrazok.zmenVelkost(this.velkostStrany, this.velkostStrany);
        this.obrazok.zmenPolohu(this.x + this.velkostStrany / 2, this.y + this.velkostStrany / 2);
        this.obrazok.zobraz();
    }

    public void nastavCislo(int cislo) {
        this.nastavObrazok(Obrazky.getCislo(cislo));
    }
}
