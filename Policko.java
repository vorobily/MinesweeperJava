public class Policko extends UIPrvok{
    private ObsahPolicka obsahPolicka;
    private StavPolicka stavPolicka;
    private Obrazok obrazok;

    private int velkostStrany;
    private int x;
    private int y;
    private int minyVOkoli;
    
    public Policko(int velkostStrany, int x, int y) {
        super(velkostStrany, velkostStrany, x, y);

        this.obsahPolicka = ObsahPolicka.VOLNE;
        this.nastavStav(StavPolicka.SKYRTE);

        this.velkostStrany = velkostStrany;
        this.x = x;
        this.y = y;
        this.minyVOkoli = 0;

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
            if(this.stavPolicka != StavPolicka.ZOBRAZENE) {
                this.nastavStav((this.stavPolicka == StavPolicka.SKYRTE) ? StavPolicka.VLAJKA : StavPolicka.SKYRTE);
            }
            return;
        }

        if (this.stavPolicka == StavPolicka.SKYRTE) {
            this.nastavStav(StavPolicka.ZOBRAZENE);
        }
    }

    public void nastavMinyVOkoli(int minyVOkoli) {
        this.minyVOkoli = minyVOkoli;
    }

    private void nastavStav(StavPolicka stavPolicka) {
        if (this.obrazok != null) {
            this.obrazok.skry();
            this.obrazok = null;
        }

        this.stavPolicka = stavPolicka;
        super.zmenFarbu(stavPolicka.getFarba());

        switch(stavPolicka) {
            case SKYRTE:
                break;
            case VLAJKA:
                this.nastavObrazok(Obrazky.VLAJKA);
                break;
            default:
                if (this.obsahPolicka == ObsahPolicka.MINA) {
                    this.nastavObrazok(Obrazky.MINA);
                } else {
                    this.nastavCislo(this.minyVOkoli);
                }
                break;
        }
    }
}
