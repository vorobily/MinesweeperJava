public enum Tlacidla {
    NOVA(85, "Nová hra"),
    RESTART(75, "Restart"),
    NAPOVEDA(95, "Nápoveda"),
    KONEC(70, "Konec");

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