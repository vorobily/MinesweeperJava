public enum Tlacidla {
    NOVA(85, "Nová hra"),
    RESTART(75, "Reštart"),
    NAPOVEDA(95, "Nápoveda"),
    REKORD(75, "Rekord"),
    KONIEC(70, "Koniec");

    private int sirka;
    private String text;

    Tlacidla(int sirka, String text) {
        this.sirka = sirka;
        this.text = text;
    }

    public int getSirka() {
        return this.sirka;
    }

    public String getText() {
        return this.text;
    }
}