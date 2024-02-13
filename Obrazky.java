public enum Obrazky {
    MINA("pics/Mine.png"),
    MINAVYB("pics/MineExploded.png"),
    VLAJKA("pics/Flag.png"),
    CISLO1("pics/Number1.png"),
    CISLO2("pics/Number2.png"),
    CISLO3("pics/Number3.png"),
    CISLO4("pics/Number4.png"),
    CISLO5("pics/Number5.png"),
    CISLO6("pics/Number6.png"),
    CISLO7("pics/Number7.png"),
    CISLO8("pics/Number8.png"),
    POZADI("pics/Background.png");

    private String cesta;

    public static Obrazky getCislo(int cislo) {
        if (cislo < 1 || cislo > 8) { //Zobrazi čísla od 1 do 8
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
