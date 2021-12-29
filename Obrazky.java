public enum Obrazky {
    MINA("pics/mina.png"),
    MINA2("pics/minaVybuch.png"),
    VLAJKA("pics/vlajka.png"),
    CISLO1("pics/cislica1.png"),
    CISLO2("pics/cislica2.png"),
    CISLO3("pics/cislica3.png"),
    CISLO4("pics/cislica4.png"),
    CISLO5("pics/cislica5.png"),
    CISLO6("pics/cislica6.png"),
    CISLO7("pics/cislica7.png"),
    CISLO8("pics/cislica8.png"),
    POZADIE("pics/pozadie.png");

    private String cesta;

    public static Obrazky getCislo(int cislo) {
        if (cislo < 1 || cislo > 8) { //Zobrazuje čísla iba od 1 po 8
            return null;
        }

        return Obrazky.values()[cislo + 2];
    }

    Obrazky(String cesta) {
        this.cesta = cesta;
    }

    public String getCesta() {
        return this.cesta;
    }

}
