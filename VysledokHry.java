public enum VysledokHry {
    PREBIEHA(""),
    VYHRAL("Vyhral si"),
    PREHRAL("Prehral si");

    private String vyhernaSprava;

    private VysledokHry(String vyhernaSprString) {
        this.vyhernaSprava = vyhernaSprString;
    }

    public String getVyhernaSprava() {
        return this.vyhernaSprava;
    }
}
