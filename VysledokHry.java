public enum VysledokHry {
    PREBIEHA("Hra prebieha"),
    VYHRAL("Vyhral si"),
    PREHRAL("Prehral si");

    private String vyhernaSprava;

    VysledokHry(String vyhernaSprString) {
        this.vyhernaSprava = vyhernaSprString;
    }

    public String getVyhernaSprava() {
        return this.vyhernaSprava;
    }
}
