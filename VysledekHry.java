public enum VysledekHry {
    PROBIHA("Hra probíhá"),
    VYHRAL("Vyhrál"),
    PROHRAL("Prohrál");

    private String vyhernaZprava;

    VysledekHry(String vyhernaSpravaString) {
        this.vyhernaZprava = vyhernaSpravaString;
    }

    public String getVyhernaZprava() {
        return this.vyhernaZprava;
    }
}
