public enum Udaje {
    MINUTY("minuty", -1),
    SEKUNDY("sekundy", -1),
    MINY("pocetMin", 4),
    ROZMER("rozmer", 4);

    private String string;
    private int pocatecniHodnota;

    Udaje(String string, int pocatecniHodnota) {
        this.string = string;
        this.pocatecniHodnota = pocatecniHodnota;
    }

    public String getString() {
        return this.string;
    }

    public int getPocatecniHodnota() {
        return this.pocatecniHodnota;
    }
}
