public class Policko extends UIPrvok{
    private ObsahPolicka obsahPolicka;
    private StavPolicka stavPolicka;
    private Obrazok obrazok;

    private int velkostStrany;
    private int x;
    private int y;
    
    public Policko(int velkostStrany, int x, int y) {
        super(velkostStrany, velkostStrany, x, y);

        this.obsahPolicka = ObsahPolicka.VOLNE;
        this.nastavStav(StavPolicka.SKYRTE);

        this.velkostStrany = velkostStrany;
        this.x = x;
        this.y = y;

        super.zobraz();
    }

    public boolean obsahujeSuradnice(int x, int y) {
        return x >= this.x && (this.x + this.velkostStrany) > x && y >= this.y && (this.y + this.velkostStrany) > y;
    }

    public ObsahPolicka getObsahPolicka() {
        return this.obsahPolicka;
    }

    public StavPolicka getStavPolicka() {
        return this.stavPolicka;
    }

    public void nastavObsahPolicka(ObsahPolicka obsahPolicka) {
        this.obsahPolicka = obsahPolicka;
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

    public void klik(boolean laveTlacidlo) {
        if (!laveTlacidlo) {
            //TODO: vlajka
            return;
        }

        if (this.stavPolicka == StavPolicka.SKYRTE) {
            this.nastavStav(StavPolicka.ZOBRAZENE);
        }
    }

    private void nastavStav(StavPolicka stavPolicka) {
        this.stavPolicka = stavPolicka;
        super.zmenFarbu(stavPolicka.getFarba());
    }
}
