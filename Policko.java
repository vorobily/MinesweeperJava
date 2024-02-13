public class Policko extends UIPrvek {
    private ObsahPolicka obsahPolicka;
    private StavPolicka stavPolicka;
    private Obrazek obrazek;

    private int velikostStrany;
    private int x;
    private int y;
    private int minyVOkoli;
    
    public Policko(int velikostStrany, int x, int y) {
        super(velikostStrany, velikostStrany, x, y);

        this.obsahPolicka = ObsahPolicka.VOLNE;
        this.nastavStav(StavPolicka.SKRYTE);

        this.velikostStrany = velikostStrany;
        this.x = x;
        this.y = y;
        this.minyVOkoli = 0;

        super.zobraz();
    }

    public boolean obsahujesouradnice(int x, int y) {
        return x >= this.x && (this.x + this.velikostStrany) > x && y >= this.y && (this.y + this.velikostStrany) > y;
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

    public void nastavobrazek(Obrazky obrazky) {
        if (this.obrazek != null) {
            this.obrazek.skryt();
        }

        if (obrazky == null) {
            return;
        }
        
        this.obrazek = new Obrazek(obrazky.getCesta());
        this.obrazek.zmenvelikost(this.velikostStrany, this.velikostStrany);
        this.obrazek.zmenPolohu(this.x + this.velikostStrany / 2, this.y + this.velikostStrany / 2);
        this.obrazek.zobraz();
    }

    public void nastavCislo(int cislo) {
        this.nastavobrazek(Obrazky.getCislo(cislo));
    }

    public void klik(boolean leveTlacidlo) {
        if (!leveTlacidlo) {
            if (this.stavPolicka != StavPolicka.ZOBRAZENE) {
                this.nastavStav((this.stavPolicka == StavPolicka.SKRYTE) ? StavPolicka.VLAJKA : StavPolicka.SKRYTE);
            }
            return;
        }

        if (this.stavPolicka == StavPolicka.SKRYTE) {
            this.nastavStav(StavPolicka.ZOBRAZENE);
        }
    }

    public void nastavMinyVOkoli(int minyVOkoli) {
        this.minyVOkoli = minyVOkoli;
    }

    public int getMinyVOkoli() {
        return this.minyVOkoli;
    }

    public void nastavStav(StavPolicka stavPolicka) {
        if (this.obrazek != null) {
            this.obrazek.skryt();
            this.obrazek = null;
        }

        this.stavPolicka = stavPolicka;
        super.zmenBarvu(stavPolicka.getbarva());

        switch (stavPolicka) {
            case SKRYTE:
                break;
            case VLAJKA:
                this.nastavobrazek(Obrazky.VLAJKA);
                break;
            default:
                if (this.obsahPolicka == ObsahPolicka.MINA) {
                    this.nastavobrazek(Obrazky.MINA);
                } else {
                    this.nastavCislo(this.minyVOkoli);
                }
                break;
        }
    }

    @Override
    public void skryt() {
        super.skryt();
        if (this.obrazek != null) {
            this.obrazek.skryt();
        }
    }

    @Override
    public void zobraz() {
        super.zobraz();
        if (this.obrazek != null) {
            this.obrazek.zobraz();
        }
    }
}
