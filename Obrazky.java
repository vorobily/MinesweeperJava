public enum Obrazky {
    MINA("pics/obrazok.png"),
    VLAJKA("pics/obrazok.png"),
    CISLO1("pics/obrazok.png"),
    CISLO2("pics/obrazok.png"),
    CISLO3("pics/obrazok.png"),
    CISLO4("pics/obrazok.png"),
    CISLO5("pics/obrazok.png"),
    CISLO6("pics/obrazok.png"),
    CISLO7("pics/obrazok.png"),
    CISLO8("pics/obrazok.png");

    private String cesta;

    public static Obrazky getCislo(int cislo) {
        if(cislo < 1 || cislo > 8) {
            return null;
        }

        return Obrazky.values()[cislo + 1];
    }

    Obrazky(String cesta) {
        this.cesta = cesta;
    }

    public String getCesta() {
        return this.cesta;
    }

}
